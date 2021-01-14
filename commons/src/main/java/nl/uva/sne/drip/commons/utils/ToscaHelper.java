/*
 * Copyright 2019 S. Koulouzis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.uva.sne.drip.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.KeyPair;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.sne.drip.model.Exceptions.TypeExeption;
import nl.uva.sne.drip.model.NodeTemplate;
import nl.uva.sne.drip.model.NodeTemplateMap;
import nl.uva.sne.drip.model.tosca.Credential;
import nl.uva.sne.drip.model.tosca.ToscaTemplate;
import nl.uva.sne.drip.sure.tosca.client.DefaultApi;
import org.apache.commons.io.FileUtils;
import nl.uva.sne.drip.sure.tosca.client.ApiException;
import nl.uva.sne.drip.sure.tosca.client.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import static nl.uva.sne.drip.commons.utils.Constants.*;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormSubTopology.StatusEnum;
import nl.uva.sne.drip.model.cloud.storm.OpCode;

/**
 *
 * @author S. Koulouzis
 */
public class ToscaHelper {

    private DefaultApi api;

    private ObjectMapper objectMapper;

    private Integer id;

    @Autowired
    public ToscaHelper(String sureToscaBasePath) {
        init(sureToscaBasePath);
    }

    public static Boolean isServiceUp(String serviceBasePath) {
        try {
            URL serviceUrl = new URL(serviceBasePath);
            HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
            //Set request to header to reduce load as Subirkumarsao said.
            connection.setRequestMethod("HEAD");
            int code = connection.getResponseCode();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    private void init(String sureToscaBasePath) {
        Configuration.getDefaultApiClient().setBasePath(sureToscaBasePath);
        Configuration.getDefaultApiClient().setConnectTimeout(1200000);
        Logger.getLogger(ToscaHelper.class.getName()).log(Level.FINE, "sureToscaBasePath: {0}", Configuration.getDefaultApiClient().getBasePath());
        api = new DefaultApi(Configuration.getDefaultApiClient());
        this.objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public void uploadToscaTemplate(ToscaTemplate toscaTemplate) throws JsonProcessingException, IOException, ApiException {
        String ymlStr = objectMapper.writeValueAsString(toscaTemplate);
        File toscaTemplateFile = File.createTempFile("temp-toscaTemplate", ".yml");
        FileUtils.writeByteArrayToFile(toscaTemplateFile, ymlStr.getBytes());
        Logger.getLogger(ToscaHelper.class.getName()).log(Level.FINE, "Uploading ToscaTemplate to sure-tosca service: {0}", api.getApiClient().getBasePath());
        String resp = api.uploadToscaTemplate(toscaTemplateFile);
        id = Integer.valueOf(resp);
        toscaTemplateFile.deleteOnExit();
        Logger.getLogger(ToscaHelper.class.getName()).log(Level.FINE, "Uploaded ToscaTemplate to sure-tosca service got back id: {0}", id);
    }

    public List<Map<String, Object>> getProvisionInterfaceDefinitions(List<String> toscaInterfaceTypes) throws ApiException {
        List<Map<String, Object>> interfaceDefinitions = new ArrayList<>();
        for (String type : toscaInterfaceTypes) {
            String derivedFrom = null;
            List<Map<String, Object>> interfaces = api.getTypes(String.valueOf(id), "interface_types", null, type, null, null, null, null, null, derivedFrom);
            interfaceDefinitions.addAll(interfaces);
        }
        return interfaceDefinitions;
    }

    public List<NodeTemplateMap> getVMTopologyTemplates() throws ApiException {

        try {
            List<NodeTemplateMap> vmTopologyTemplates = api.getNodeTemplates(String.valueOf(id), VM_TOPOLOGY, null, null, null, null, null, null, null);
            return vmTopologyTemplates;
        } catch (ApiException ex) {
            if (ex.getCode() == 404) {
                return null;
            }
            throw ex;
        }
    }

    public List<NodeTemplateMap> getApplicationTemplates() throws ApiException {
        try {
            List<NodeTemplateMap> vmTopologyTemplates = api.getNodeTemplates(String.valueOf(id), APPLICATION_TYPE, null, null, null, null, null, null, null);
            return vmTopologyTemplates;
        } catch (ApiException ex) {
            if (ex.getCode() == 404) {
                return null;
            }
            throw ex;
        }
    }

    public List<NodeTemplateMap> getTemplateVMsForVMTopology(NodeTemplateMap nodeTemplateMap) throws ApiException {
        NodeTemplate nodeTemplate = nodeTemplateMap.getNodeTemplate();
        List<Map<String, Object>> requirements = nodeTemplate.getRequirements();
        List<NodeTemplateMap> vms = new ArrayList<>();
        for (Map<String, Object> req : requirements) {
            String nodeName = req.keySet().iterator().next();
            Map<String, Object> node = (Map<String, Object>) req.get(nodeName);
            if (node.get("capability").equals(VM_CAPABILITY)) {
                String vmName = (String) node.get("node");
                List<NodeTemplateMap> vmNodeTemplates = api.getNodeTemplates(String.valueOf(id), null, vmName, null, null, null, null, null, null);
                vms.addAll(vmNodeTemplates);
            }
        }
        return vms;

    }

    public Double getVMNumOfCores(NodeTemplateMap vmMap) throws Exception {
        NodeTemplate vm = vmMap.getNodeTemplate();
        if (vm.getType().equals(VM_TYPE)) {
            return (Double) vm.getProperties().get(VM_NUM_OF_CORES);
        } else {
            throw new Exception("NodeTemplate is not of type: " + VM_TYPE + " it is of type: " + vm.getType());
        }
    }

    public Double getVMNMemSize(NodeTemplateMap vmMap) throws Exception {
        NodeTemplate vm = vmMap.getNodeTemplate();
        if (vm.getType().equals(VM_TYPE)) {
            String memScalar = (String) vm.getProperties().get(MEM_SIZE);
            String[] memScalarArray = memScalar.split(" ");
            String memSize = memScalarArray[0];
            String memUnit = memScalarArray[1];
            Double memSizeInGB = convertToGB(Integer.valueOf(memSize), memUnit);
            return memSizeInGB;
        } else {
            throw new Exception("NodeTemplate is not of type: " + VM_TYPE + " it is of type: " + vm.getType());
        }

    }

    public Double getVMNDiskSize(NodeTemplateMap vmMap) throws Exception {
        NodeTemplate vm = vmMap.getNodeTemplate();
        if (vm.getType().equals(VM_TYPE)) {
            String memScalar = (String) vm.getProperties().get(DISK_SIZE);
            String[] memScalarArray = memScalar.split(" ");
            String memSize = memScalarArray[0];
            String memUnit = memScalarArray[1];
            Double sizeInGB = convertToGB(Integer.valueOf(memSize), memUnit);
            return sizeInGB;
        } else {
            throw new Exception("NodeTemplate is not of type: " + VM_TYPE + " it is of type: " + vm.getType());
        }

    }

    public String getVMNOS(NodeTemplateMap vmMap) throws Exception {
        NodeTemplate vm = vmMap.getNodeTemplate();
        if (vm.getType().equals(VM_TYPE)) {
            return (String) vm.getProperties().get(VM_OS);
        } else {
            throw new Exception("NodeTemplate is not of type: " + VM_TYPE + " it is of type: " + vm.getType());
        }
    }

    private Double convertToGB(Integer value, String memUnit) {
        switch (memUnit) {
            case "GB":
                return Double.valueOf(value);
            case "MB":
                return value * 0.001;
            case "KB":
                return value * 0.000001;
            default:
                return null;
        }
    }

    public String getTopologyDomain(NodeTemplateMap nodeTemplateMap) throws TypeExeption {
        NodeTemplate nodeTemplate = nodeTemplateMap.getNodeTemplate();
        if (nodeTemplate.getType().equals(VM_TOPOLOGY)) {
            return (String) nodeTemplate.getProperties().get("domain");
        } else {
            throw new TypeExeption("NodeTemplateMap is not of type: " + VM_TOPOLOGY + " it is of type: " + nodeTemplate.getType());
        }
    }

    public String getTopologyProvider(NodeTemplateMap nodeTemplateMap) throws TypeExeption {
        NodeTemplate nodeTemplate = nodeTemplateMap.getNodeTemplate();
        if (nodeTemplate.getType().equals(VM_TOPOLOGY)) {
            return (String) nodeTemplate.getProperties().get("provider");
        } else {
            throw new TypeExeption("NodeTemplate is not of type: " + VM_TOPOLOGY + " it is of type: " + nodeTemplate.getType());
        }
    }

    public NodeTemplateMap setCredentialsInVMTopology(NodeTemplateMap vmTopologyMap, Credential credential) throws TypeExeption {
        NodeTemplate vmTopology = vmTopologyMap.getNodeTemplate();
        if (vmTopology.getType().equals(VM_TOPOLOGY)) {
            Map<String, Object> att = vmTopology.getAttributes();
            if (att == null) {
                att = new HashMap<>();
            }
            att.put("credential", credential);
            vmTopology.setAttributes(att);
            vmTopologyMap.setNodeTemplate(vmTopology);
            return vmTopologyMap;
        } else {
            throw new TypeExeption("NodeTemplate is not of type: " + VM_TOPOLOGY + " it is of type: " + vmTopology.getType());
        }
    }

    public Credential getCredentialsFromVMTopology(NodeTemplateMap vmTopologyMap) throws Exception {
        NodeTemplate vmTopology = vmTopologyMap.getNodeTemplate();
        if (vmTopology.getType().equals(VM_TOPOLOGY)) {
            Map<String, Object> att = vmTopology.getAttributes();
            String ymlStr = Converter.map2YmlString((Map<String, Object>) att.get("credential"));
            Credential toscaCredential = objectMapper.readValue(ymlStr, Credential.class);
            return toscaCredential;

        } else {
            throw new TypeExeption("NodeTemplate is not of type: " + VM_TOPOLOGY + " it is of type: " + vmTopology.getType());
        }
    }

    public ToscaTemplate setNodeInToscaTemplate(ToscaTemplate toscaTemplate, NodeTemplateMap node) {
        Map<String, NodeTemplate> nodes = toscaTemplate.getTopologyTemplate().getNodeTemplates();
        nodes.put(node.getName(), node.getNodeTemplate());
        return toscaTemplate;
    }

    public Map<String, Object> getProvisionerInterfaceFromVMTopology(NodeTemplateMap vmTopologyMap) {
        return (Map<String, Object>) vmTopologyMap.getNodeTemplate().getInterfaces().get("CloudsStorm");
    }

    public NodeTemplateMap setProvisionerInterfaceInVMTopology(NodeTemplateMap vmTopologyMap, Map<String, Object> provisionerInterface) {
        vmTopologyMap.getNodeTemplate().setInterfaces(provisionerInterface);
        return vmTopologyMap;
    }

    public String getVMTopologyUser() throws ApiException {
        List<NodeTemplateMap> vmTopologyTemplatesMap = getVMTopologyTemplates();
        for (NodeTemplateMap nodeTemplateMap : vmTopologyTemplatesMap) {
            List<NodeTemplateMap> vmTemplatesMap = getTemplateVMsForVMTopology(nodeTemplateMap);
            for (NodeTemplateMap vmMap : vmTemplatesMap) {
                Map<String, Object> prop = vmMap.getNodeTemplate().getProperties();
                if (prop != null && prop.containsKey("user_name")) {
                    return (String) prop.get("user_name");
                }
            }
        }
        return "vm_user";
    }

    public NODE_STATES getNodeCurrentState(NodeTemplateMap node) {
        return getNodeState(node, "current_state");
    }

    public NodeTemplateMap setNodeCurrentState(NodeTemplateMap node, NODE_STATES nodeState) {
        return setNodeState(node, "current_state", nodeState);
    }

    public NodeTemplateMap setNodeDesiredState(NodeTemplateMap node, NODE_STATES nodeState) {
        return setNodeState(node, "desired_state", nodeState);
    }

    public NODE_STATES getNodeDesiredState(NodeTemplateMap node) {
        return getNodeState(node, "desired_state");
    }

    private NODE_STATES getNodeState(NodeTemplateMap node, String stateName) {
        Map<String, Object> attributes = node.getNodeTemplate().getAttributes();
        if (attributes != null && attributes.containsKey(stateName)) {
            return NODE_STATES.valueOf((String) attributes.get(stateName));
        }
        return null;
    }

    private NodeTemplateMap setNodeState(NodeTemplateMap node, String stateName, NODE_STATES nodeState) {
        Map<String, Object> attributes = node.getNodeTemplate().getAttributes();
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        if (nodeState != null) {
            attributes.put(stateName, nodeState.toString());
            node.getNodeTemplate().attributes(attributes);
        }

        return node;
    }

    public static NODE_STATES cloudStormStatus2NodeState(StatusEnum cloudStormStatus) {
        if (cloudStormStatus.equals(StatusEnum.FRESH)) {
            return null;
        }
        String cloudStormStatusStr = cloudStormStatus.toString().toUpperCase();
        return NODE_STATES.valueOf(cloudStormStatusStr);
    }

    public KeyPair getKeyPairsFromVM(NodeTemplate vmMap) throws ApiException, TypeExeption, JSchException {
        if (vmMap.getType().equals(VM_TYPE)) {
            Map<String, Object> attributes = vmMap.getAttributes();
            if (attributes != null && attributes.containsKey("user_key_pair")) {
                Map<String, Object> userKeyPair = (Map<String, Object>) attributes.get("user_key_pair");
                if (userKeyPair.containsKey("protocol") && userKeyPair.get("protocol").equals("ssh")) {
                    Map<String, Object> keysMap = (Map<String, Object>) userKeyPair.get("keys");
                    JSch jsch = new JSch();
                    byte[] privatekeyBytes = Base64.getDecoder().decode(((String) keysMap.get("private_key")));
                    byte[] publicKeyBytes = Base64.getDecoder().decode(((String) keysMap.get("public_key")));
                    KeyPair keyPair = KeyPair.load(jsch, privatekeyBytes, publicKeyBytes);
                    keyPair.dispose();
                    return keyPair;
                }
            }

        } else {
            throw new TypeExeption("NodeTemplate is not of type: " + VM_TYPE + " it is of type: " + vmMap.getType());
        }
        return null;
    }

    public static OpCode.OperationEnum NodeDesiredState2CloudStormOperation(NODE_STATES nodeDesiredState) {
        switch (nodeDesiredState) {
            case RUNNING:
                return OpCode.OperationEnum.PROVISION;
            case DELETED:
                return OpCode.OperationEnum.DELETE;
            case STARTED:
                return OpCode.OperationEnum.START;
            case STOPPED:
                return OpCode.OperationEnum.STOP;
            case H_SCALED:
                return OpCode.OperationEnum.HSCALE;
            case V_SCALED:
                return OpCode.OperationEnum.VSCALE;
            default:
                return null;
        }
    }

    public static StatusEnum nodeCurrentState2CloudStormStatus(NODE_STATES currentState) {
        if (currentState == null) {
            return StatusEnum.FRESH;
        }
        switch (currentState) {
            case RUNNING:
                return StatusEnum.RUNNING;
            case DELETED:
                return StatusEnum.DELETED;
            case STARTED:
                return StatusEnum.RUNNING;
            case STOPPED:
                return StatusEnum.STOPPED;
            case H_SCALED:
                return StatusEnum.RUNNING;
            case V_SCALED:
                return StatusEnum.RUNNING;
            case FAILED:
                return StatusEnum.FAILED;
            default:
                return null;
        }

    }

    public Map<String, Object> getNodeArtifacts(NodeTemplate nodeTemplate) {
        return nodeTemplate.getArtifacts();
    }

    public Map<String, Object> getNodeArtifact(NodeTemplate nodeTemplate, String artifactName) {
        Map<String, Object> artifacts = nodeTemplate.getArtifacts();
        if (artifacts != null) {
            return (Map<String, Object>) artifacts.get(artifactName);
        }
        return null;
    }

}
