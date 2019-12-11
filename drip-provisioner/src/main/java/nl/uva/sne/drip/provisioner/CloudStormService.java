/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.drip.provisioner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.KeyPair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nl.uva.sne.drip.commons.utils.ToscaHelper;
import nl.uva.sne.drip.model.CloudsStormSubTopology;
import nl.uva.sne.drip.model.CloudsStormTopTopology;
import nl.uva.sne.drip.model.CloudsStormVM;
import nl.uva.sne.drip.model.NodeTemplate;
import nl.uva.sne.drip.model.ToscaTemplate;
import nl.uva.sne.drip.sure_tosca.client.ApiException;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author S. Koulouzis
 */
class CloudStormService {

    private List<Map.Entry> vmTopologies;
    private String tempInputDirPath;
    private final ToscaTemplate toscaTemplate;
    private final ToscaHelper helper;

    CloudStormService(String sureToscaBasePath, ToscaTemplate toscaTemplate) throws IOException, JsonProcessingException, ApiException {
        this.toscaTemplate = toscaTemplate;
        this.helper = new ToscaHelper(toscaTemplate, sureToscaBasePath);
    }

    public ToscaTemplate execute() throws FileNotFoundException, JSchException, IOException, ApiException {
        tempInputDirPath = System.getProperty("java.io.tmpdir") + File.separator + "Input-" + Long.toString(System.nanoTime()) + File.separator;
        File tempInputDir = new File(tempInputDirPath);
        if (!(tempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + tempInputDir.getAbsolutePath());
        }

        buildCloudStormTopTopology(toscaTemplate);

        return toscaTemplate;
    }

    private CloudsStormTopTopology buildCloudStormTopTopology(ToscaTemplate toscaTemplate) throws JSchException, IOException, ApiException {
        CloudsStormTopTopology topTopology = new CloudsStormTopTopology();
        String publicKeyPath = buildSSHKeyPair();
        topTopology.setPublicKeyPath(publicKeyPath);
        topTopology.setUserName(getUserName());

        List<CloudsStormSubTopology> topologies = getCloudsStormSubTopologies(toscaTemplate);
        topTopology.setTopologies(topologies);

        return topTopology;
    }

    private String buildSSHKeyPair() throws JSchException, IOException {
        String userPublicKeyName = "id_rsa.pub";
        String publicKeyPath = "name@" + userPublicKeyName;
        JSch jsch = new JSch();
        KeyPair kpair = KeyPair.genKeyPair(jsch, KeyPair.RSA);
        String userPrivateName = FilenameUtils.removeExtension(userPublicKeyName);
        kpair.writePrivateKey(tempInputDirPath + File.separator + userPrivateName);
        kpair.writePublicKey(tempInputDirPath + File.separator + userPublicKeyName, "auto generated user accees keys");
        kpair.dispose();
        return publicKeyPath;
    }

    private String getUserName() {
        return "vm_user";
    }

    private List<CloudsStormSubTopology> getCloudsStormSubTopologies(ToscaTemplate toscaTemplate) throws ApiException {
        List<NodeTemplate> vmTopologyTemplates = helper.getVMTopologyTemplates();
        List<CloudsStormSubTopology> cloudsStormSubTopologies = new ArrayList<>();
        int i = 0;
        for (NodeTemplate nodeTemplate : vmTopologyTemplates) {
            CloudsStormSubTopology cloudsStormSubTopology = new CloudsStormSubTopology();

            Map<String, Object> properties = nodeTemplate.getProperties();

            String domain = (String) properties.get("domain");
            String provider = (String) properties.get("provider");
            cloudsStormSubTopology.setDomain(domain);
            cloudsStormSubTopology.setCloudProvider(provider);
            cloudsStormSubTopology.setTopology("vm_topology" + i);
            cloudsStormSubTopology.setStatus("fresh");

            List<NodeTemplate> vmTemplates = helper.getTopologyTemplateVMs(nodeTemplate);
            List<CloudsStormVM> vms = new ArrayList<>();
            for (NodeTemplate vm : vmTemplates) {
                CloudsStormVM cloudsStormVM = new CloudsStormVM();
                
                String vmType = helper.getVMType(vm,provider);
                cloudsStormVM.setNodeType(vmType);
            }
            i++;
        }
        return cloudsStormSubTopologies;
    }

}