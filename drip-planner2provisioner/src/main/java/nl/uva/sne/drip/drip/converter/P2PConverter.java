package nl.uva.sne.drip.drip.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.sne.drip.commons.utils.Converter;
import nl.uva.sne.drip.drip.converter.provisionerIn.*;
import nl.uva.sne.drip.drip.converter.provisionerIn.EC2.*;
import nl.uva.sne.drip.drip.converter.provisionerIn.provisionerIn.EGI.*;
import nl.uva.sne.drip.drip.converter.provisionerIn.provisionerIn.ExoGeni.ExoGeniSubTopology;
import nl.uva.sne.drip.drip.converter.provisionerIn.provisionerIn.ExoGeni.ExoGeniVM;

import org.json.JSONException;

public class P2PConverter {

    public static SimplePlanContainer transfer(Map<String, Object> toscaPlanMap,
            String userName, String domainName, String cloudProvider) throws JsonParseException, JsonMappingException, IOException, JSONException {
        if (cloudProvider != null) {
            cloudProvider = cloudProvider.toUpperCase();
        }

        Map<String, Object> topologyTemplate = (Map<String, Object>) ((Map<String, Object>) toscaPlanMap.get("topology_template")).get("node_templates");

        //Get the domain provider and vm list
        Map<String, Object> topologyNode = null;
        String topologyNodeName = null;
        List<String> vmNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : topologyTemplate.entrySet()) {
            Map<String, Object> node = (Map<String, Object>) entry.getValue();
            String type = (String) node.get("type");
            if (type.equals("tosca.nodes.ARTICONF.VM.topology")) {
                topologyNode = node;
                topologyNodeName = entry.getKey();
                Map<String, Object> properties = (Map<String, Object>) topologyNode.get("properties");
                domainName = (String) properties.get("domain");
                cloudProvider = (String) properties.get("provider");
                List<Object> requirements = (List<Object>) topologyNode.get("requirements");

                for (Object requirement : requirements) {
                    Map<String, Object> requirementMap = (Map<String, Object>) requirement;

                    Map.Entry<String, Object> requirementEntry = requirementMap.entrySet().iterator().next();
                    String nodeName = (String) ((Map<String, Object>) requirementMap.get(requirementEntry.getKey())).get("node");
                    vmNames.add(nodeName);
                }
                break;
            }
        }
        List<Map<String, Object>> vmList = new ArrayList<>();
        for (String vmName : vmNames) {
            Map<String, Object> vm = (Map<String, Object>) topologyTemplate.get(vmName);
            Map<String, Object> properties = (Map<String, Object>) vm.get("properties");
            userName = (String) properties.get("user_name");
            String hostName = (String) properties.get("host_name");
            vm.put("name", hostName);
            vmList.add(vm);
        }

        TopTopology topTopology = new TopTopology();
        topTopology.publicKeyPath = "name@id_rsa.pub";
        topTopology.userName = userName;
        topTopology.topologies = new ArrayList<>();

        boolean firstVM = true;
        SubTopologyInfo subTopologyInfo = new SubTopologyInfo();
        String provisionerScalingMode = "fixed";
        SubTopology subTopology = createSubTopology(cloudProvider);
        subTopologyInfo.cloudProvider = cloudProvider;
        subTopologyInfo.topology = topologyNodeName.replaceAll("_", "");
        subTopologyInfo.domain = domainName;
        subTopologyInfo.status = "fresh";
        subTopologyInfo.statusInfo = null;
        subTopologyInfo.tag = provisionerScalingMode;

        Map<String, SubTopologyInfo> subTopologyInfos = new HashMap<>();
        int counter = 0;
        for (Object element : vmList) {
            VM vm = createVM(element, cloudProvider, firstVM);
            firstVM = false;
            counter++;
            if (isScalable(element)) {
                subTopologyInfo = new SubTopologyInfo();
                subTopology = createSubTopology(cloudProvider);
                provisionerScalingMode = "scaling";
                subTopologyInfo.cloudProvider = cloudProvider;
                subTopologyInfo.topology = "subTopology"+counter;
                subTopologyInfo.domain = domainName;
                subTopologyInfo.status = "fresh";
                subTopologyInfo.tag = provisionerScalingMode;
                subTopologyInfo.statusInfo = null;
            } else {
                for (SubTopologyInfo info : subTopologyInfos.values()) {
                    if (!info.tag.equals("scaling")) {
                        subTopologyInfo = info;
                        subTopology = subTopologyInfo.subTopology;
                        break;
                    }
                }
            }
            subTopology = addVMToSubTopology(cloudProvider, vm, subTopology);
            if (cloudProvider.trim().toLowerCase().equals("ec2")) {
                Subnet s = new Subnet();
                s.name = "s1";
                s.subnet = "192.168.10.0";
                s.netmask = "255.255.255.0";
                subTopology.subnets = new ArrayList<>();
                subTopology.subnets.add(s);
            }
            subTopologyInfo.subTopology = subTopology;
            subTopologyInfos.put(subTopologyInfo.topology, subTopologyInfo);
        }
        for (SubTopologyInfo info : subTopologyInfos.values()) {
            topTopology.topologies.add(info);
        }

        SimplePlanContainer spc = generateInfo(topTopology);

        return spc;
    }

    private static SimplePlanContainer generateInfo(TopTopology topTopology) throws JsonProcessingException {
        SimplePlanContainer spc = new SimplePlanContainer();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        String yamlString = mapper.writeValueAsString(topTopology);
        spc.topLevelContents = yamlString.substring(4);

        Map<String, Object> output = new HashMap<>();
        for (int i = 0; i < topTopology.topologies.size(); i++) {
            String key = topTopology.topologies.get(i).topology;
            String value = mapper.writeValueAsString(topTopology.topologies.get(i).subTopology);
            output.put(key, value.substring(4));
        }

        spc.lowerLevelContents = output;

        return spc;
    }

    private static int analyzeRequirements(Map<String, String> map) {
        int size = 5;

        Number memSize = Converter.castToNumber(map.get("mem_size"));
        Number num_cpus = Converter.castToNumber(map.get("num_cpus"));
        if (num_cpus.intValue() >= 16 && memSize.intValue() >= 32000) {
            size = 10;
        }
        return size;

    }

    private static String getSize(Map<String, String> map, String cloudProvider) {
        int size = analyzeRequirements(map);
        switch (cloudProvider.trim().toLowerCase()) {
            case "ec2":
                if (size <= 1) {
                    return "t2.nano";
                }
                if (size > 1 && size <= 2) {
                    return "t2.micro";
                }
                if (size > 2 && size <= 3) {
                    return "t2.small";
                }
                if (size > 3 && size <= 4) {
                    return "t2.medium";
                }
                if (size > 3 && size <= 4) {
                    return "t2.medium";
                }
                if (size > 4 && size <= 5) {
                    return "t2.medium";
                }
                if (size > 5 && size <= 6) {
                    return "t2.large";
                }
                if (size > 6 && size <= 7) {
                    return "t2.xlarge";
                }

                if (size > 7) {
                    return "t2.2xlarge";
                }
                return "t2.medium";
            case "egi":
                if (size <= 1) {
                    return "small";
                }
                if (size > 1 && size <= 5) {
                    return "medium";
                }
                if (size > 5 && size <= 10) {
                    return "mammoth";
                }
            case "exogeni":
                if (size <= 1) {
                    return "XOSmall";
                }
                if (size > 1 && size <= 5) {
                    return "XOMedium";
                }
                if (size > 5 && size <= 10) {
                    return "XOLarge";
                }
            default:
                Logger.getLogger(P2PConverter.class.getName()).log(Level.WARNING, "The {0} is not supported yet!", cloudProvider);
                return null;
        }

    }

    private static VM createVM(Object element, String cloudProvider, boolean firstVM) throws JSONException, IOException {
        VM curVM;
        switch (cloudProvider.trim().toLowerCase()) {
            case "ec2":
                curVM = new EC2VM();
                break;
            case "egi":
                curVM = new EGIVM();
                break;
            case "exogeni":
                curVM = new ExoGeniVM();
                break;
            default:
                Logger.getLogger(P2PConverter.class.getName()).log(Level.WARNING, "The {0} is not supported yet!", cloudProvider);
                return null;
        }
        Map<String, Object> map = null;
        if (element instanceof Map) {
            map = (Map<String, Object>) element;
        } else if (element instanceof String) {
            map = Converter.jsonString2Map((String) element);
        }
        curVM.name = (String) map.get("name");
        if (curVM.name == null) {
            curVM.name = "node_vm";
        }
        curVM.type = (String) map.get("type");
        Map<String, String> properties = (Map<String, String>) map.get("properties");

        curVM.OStype = (String) properties.get("os");
//            curVM.clusterType = clusterType;
//            curVM.dockers = curValue.getDocker();
        curVM.nodeType = getSize(properties, cloudProvider);

//                Eth eth = new Eth();
//                eth.name = "p1";
//                eth.subnetName = "s1";
//                int hostNum = 10 + vi;
//                String priAddress = "192.168.10." + hostNum;
//                eth.address = priAddress;
//                curVM.ethernetPort = new ArrayList<Eth>();
//                curVM.ethernetPort.add(eth);           
        if (firstVM) {
            curVM.role = "master";
        } else {
            curVM.role = "slave";
        }

        return curVM;
    }

    private static SubTopology createSubTopology(String cloudProvider) {
        SubTopology subTopology;
        switch (cloudProvider.trim().toLowerCase()) {
            case "ec2":
                subTopology = new EC2SubTopology();
                ((EC2SubTopology) subTopology).components = new ArrayList<>();
                break;
            case "egi":
                subTopology = new EGISubTopology();
                ((EGISubTopology) subTopology).components = new ArrayList<>();
                break;
            case "exogeni":
                subTopology = new ExoGeniSubTopology();
                ((ExoGeniSubTopology) subTopology).components = new ArrayList<>();
                break;
            default:
                Logger.getLogger(P2PConverter.class.getName()).log(Level.WARNING, "The {0} is not supported yet!", cloudProvider);
                return null;
        }
        return subTopology;
    }

    private static SubTopology addVMToSubTopology(String cloudProvider, VM vm, SubTopology subTopology) {
        switch (cloudProvider.trim().toLowerCase()) {
            case "ec2":
                ((EC2SubTopology) subTopology).components.add((EC2VM) vm);
                break;
            case "egi":
                ((EGISubTopology) subTopology).components.add((EGIVM) vm);
                break;
            case "exogeni":
                ((ExoGeniSubTopology) subTopology).components.add((ExoGeniVM) vm);
                break;
            default:
                Logger.getLogger(P2PConverter.class.getName()).log(Level.WARNING, "The {0} is not supported yet!", cloudProvider);
//                    return null;
            }
        return subTopology;
    }

    private static boolean isScalable(Object element) throws JSONException, IOException {
        Map<String, Object> map = null;
        if (element instanceof Map) {
            map = (Map<String, Object>) element;
        } else if (element instanceof String) {
            map = Converter.jsonString2Map((String) element);
        }
        if (map != null && map.containsKey("scaling_mode")) {
            String scalingMode = (String) map.get("scaling_mode");
            if (!scalingMode.equals("single")) {
                return true;
            }
        }
        return true;
    }
}
