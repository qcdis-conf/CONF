/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.drip.provisioner;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.sne.drip.commons.utils.Constants;
import static nl.uva.sne.drip.commons.utils.Constants.*;
import nl.uva.sne.drip.commons.utils.Converter;
import nl.uva.sne.drip.commons.utils.ToscaHelper;
import static nl.uva.sne.drip.commons.utils.ToscaHelper.cloudStormStatus2NodeState;
import nl.uva.sne.drip.model.Exceptions.TypeExeption;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormVM;
import nl.uva.sne.drip.model.NodeTemplateMap;
import nl.uva.sne.drip.model.cloud.storm.CloudCred;
import nl.uva.sne.drip.model.cloud.storm.CloudCredentialDB;
import nl.uva.sne.drip.model.cloud.storm.CloudDB.CloudProviderEnum;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormInfrasCode;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormSubTopology;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormTopTopology;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormVMs;
import nl.uva.sne.drip.model.cloud.storm.CredentialInfo;
import nl.uva.sne.drip.model.cloud.storm.InfrasCode;
import nl.uva.sne.drip.model.cloud.storm.OpCode;
import nl.uva.sne.drip.model.tosca.Credential;
import nl.uva.sne.drip.model.tosca.ToscaTemplate;
import nl.uva.sne.drip.sure.tosca.client.ApiException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.maven.shared.utils.io.DirectoryScanner;
import topology.analysis.TopologyAnalysisMain;

/**
 *
 * @author S. Koulouzis
 */
class CloudStormService {

    /**
     * @return the helper
     */
    public ToscaHelper getHelper() {
        return helper;
    }

    /**
     * @param helper the helper to set
     */
    public void setHelper(ToscaHelper helper) {
        this.helper = helper;
    }

    private ToscaHelper helper;
    private final CloudStormDAO cloudStormDAO;
    private final ObjectMapper objectMapper;
    private final String cloudStormDBPath;
    public static final String SUB_TOPOLOGY_NAME = "subTopology";
    public static final String TOPOLOGY_FOLDER_NAME = "Topology";
    public static final String INFS_FOLDER_NAME = "Infs";
    public static final String UC_FOLDER_NAME = "UC";
    public static final String UD_FOLDER_NAME = "UD";
    public static final String APP_FOLDER_NAME = "App";
    public static final String TOP_TOPOLOGY_FILE_NAME = "_top.yml";

    public static final String TOPOLOGY_RELATIVE_PATH = File.separator
            + INFS_FOLDER_NAME + File.separator + TOPOLOGY_FOLDER_NAME + File.separator;
    private ToscaTemplate toscaTemplate;
    private String userPublicKeyName;
    public static final String INFRASTUCTURE_CODE_FILE_NAME = "infrasCode.yml";

    CloudStormService(Properties properties, ToscaTemplate toscaTemplate) throws IOException, JsonProcessingException, ApiException {
        this.toscaTemplate = toscaTemplate;
        cloudStormDBPath = properties.getProperty("cloud.storm.db.path");
        if (cloudStormDBPath == null) {
            throw new NullPointerException("cloudStormDBPath cannot be null");
        }
        cloudStormDAO = new CloudStormDAO(cloudStormDBPath);
        String sureToscaBasePath = properties.getProperty("sure-tosca.base.path");
        if (sureToscaBasePath == null) {
            throw new NullPointerException("sureToscaBasePath cannot be null");
        }
        Logger.getLogger(CloudStormService.class.getName()).log(Level.FINE, "sureToscaBasePath: {0}", sureToscaBasePath);
        this.helper = new ToscaHelper(sureToscaBasePath);
        this.helper.uploadToscaTemplate(toscaTemplate);
        this.objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public ToscaTemplate execute(boolean dryRun) throws FileNotFoundException, JSchException, IOException, ApiException, Exception {
        String tempInputDirPath = System.getProperty("java.io.tmpdir") + File.separator + "Input-" + Long.toString(System.nanoTime()) + File.separator;
        File tempInputDir = new File(tempInputDirPath);
        if (!(tempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + tempInputDir.getAbsolutePath());
        }
        boolean hasArtifacts = false;
        for (NodeTemplateMap vmTopologyMap : helper.getVMTopologyTemplates()) {
            Map<String, Object> provisionedFiles = helper.getNodeArtifact(vmTopologyMap.getNodeTemplate(), "provisioned_files");
            if (provisionedFiles != null) {
                String fileContentsBase64 = (String) provisionedFiles.get("file_contents");
                if (fileContentsBase64 != null) {
                    File zipFile = new File(tempInputDir.getParent() + File.separator + Long.toString(System.nanoTime()) + "-" + CLOUD_STORM_FILES_ZIP_SUFIX);
                    Converter.decodeBase64BToFile(fileContentsBase64, zipFile.getAbsolutePath());
                    Converter.unzipFolder(zipFile.getAbsolutePath(), tempInputDir.getAbsolutePath());

                    String infrasCodeTempInputDirPath = tempInputDirPath + File.separator + APP_FOLDER_NAME;
                    File infrasCodeFile = new File(infrasCodeTempInputDirPath + File.separator + INFRASTUCTURE_CODE_FILE_NAME);
                    CloudsStormInfrasCode cloudsStormInfrasCode = objectMapper.readValue(infrasCodeFile, CloudsStormInfrasCode.class);
                    Constants.NODE_STATES nodeDesiredState = getHelper().getNodeDesiredState(vmTopologyMap);
                    OpCode.OperationEnum operation = ToscaHelper.NodeDesiredState2CloudStormOperation(nodeDesiredState);
                    cloudsStormInfrasCode.getInfrasCodes().get(0).getOpCode().setOperation(operation);

                    objectMapper.writeValue(infrasCodeFile, cloudsStormInfrasCode);
                    hasArtifacts = true;
                    break;
                }
            }
        }

        if (!hasArtifacts) {
            initCloudStormFiles(tempInputDirPath);
        }

        ToscaTemplate newToscaTemplate = runCloudStorm(tempInputDirPath, dryRun);
        getHelper().uploadToscaTemplate(newToscaTemplate);
        return newToscaTemplate;
    }

    protected Map<String, Object> writeCloudStormTopologyFiles(String topologyTempInputDirPath) throws JSchException, IOException, ApiException, Exception {
        CloudsStormTopTopology topTopology = new CloudsStormTopTopology();

        KeyPair keyPair = getKeyPair();

        String publicKeyPath = buildSSHKeyPair(topologyTempInputDirPath, keyPair);
        topTopology.setPublicKeyPath(publicKeyPath);
        topTopology.setUserName(getHelper().getVMTopologyUser());

        Map<String, Object> subTopologiesAndVMs = getCloudsStormSubTopologiesAndVMs(topologyTempInputDirPath);
        List<CloudsStormSubTopology> cloudsStormSubTopology = (List<CloudsStormSubTopology>) subTopologiesAndVMs.get("cloud_storm_subtopologies");
        topTopology.setTopologies(cloudsStormSubTopology);
        File topTopologyFile = new File(topologyTempInputDirPath + File.separator + TOP_TOPOLOGY_FILE_NAME);
        objectMapper.writeValue(topTopologyFile, topTopology);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.INFO, "Wrote CloudStorm topology files in: {0}", topTopologyFile.getAbsolutePath());
        return subTopologiesAndVMs;
    }

    protected String buildSSHKeyPair(String tempInputDirPath, KeyPair kpair) throws JSchException, IOException {
        userPublicKeyName = "id_rsa.pub";
        String publicKeyPath = "name@" + userPublicKeyName;
        String userPrivateName = FilenameUtils.removeExtension(userPublicKeyName);
        if (kpair == null) {
            JSch jsch = new JSch();
            kpair = KeyPair.genKeyPair(jsch, KeyPair.RSA);
        }
        kpair.writePrivateKey(tempInputDirPath + File.separator + userPrivateName);
        kpair.writePublicKey(tempInputDirPath + File.separator + userPublicKeyName, "generated user accees keys");
        kpair.dispose();

        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        Files.setPosixFilePermissions(Paths.get(tempInputDirPath + File.separator + userPrivateName), perms);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.INFO, "Wrote ssh keys in: {0}{1}{2}", new Object[]{tempInputDirPath, File.separator, userPrivateName});
        return publicKeyPath;
    }

    protected Map<String, Object> getCloudsStormSubTopologiesAndVMs(String tempInputDirPath) throws ApiException, IOException, Exception {
        List<NodeTemplateMap> vmTopologyTemplatesMap = getHelper().getVMTopologyTemplates();
        List<CloudsStormSubTopology> cloudsStormSubTopologies = new ArrayList<>();
        Map<String, Object> cloudsStormMap = new HashMap<>();
        List<CloudsStormVMs> cloudsStormVMsList = new ArrayList<>();
        int i = 0;
        for (NodeTemplateMap nodeTemplateMap : vmTopologyTemplatesMap) {
            CloudsStormSubTopology cloudsStormSubTopology = new CloudsStormSubTopology();
            String domain = getHelper().getTopologyDomain(nodeTemplateMap);
            String provider = getHelper().getTopologyProvider(nodeTemplateMap);
            cloudsStormSubTopology.setDomain(domain);
            cloudsStormSubTopology.setCloudProvider(provider);
            cloudsStormSubTopology.setTopology(SUB_TOPOLOGY_NAME + i);
            Constants.NODE_STATES currentState = getHelper().getNodeCurrentState(nodeTemplateMap);
            Constants.NODE_STATES desiredState = getHelper().getNodeDesiredState(nodeTemplateMap);

            cloudsStormSubTopology.setStatus(ToscaHelper.nodeCurrentState2CloudStormStatus(currentState));
            CloudsStormVMs cloudsStormVMs = new CloudsStormVMs();

            List<CloudsStormVM> vms = new ArrayList<>();
            List<NodeTemplateMap> vmTemplatesMap = getHelper().getTemplateVMsForVMTopology(nodeTemplateMap);
            int j = 0;
            for (NodeTemplateMap vmMap : vmTemplatesMap) {
                CloudsStormVM cloudStromVM = getBestMatchingCloudStormVM(vmMap, provider);
                cloudStromVM.setName("vm" + j);
                vms.add(cloudStromVM);
                j++;
            }
            cloudsStormVMs.setVms(vms);
            objectMapper.writeValue(new File(tempInputDirPath + File.separator + SUB_TOPOLOGY_NAME + i + ".yml"), cloudsStormVMs);
            cloudsStormVMsList.add(cloudsStormVMs);
            cloudsStormSubTopologies.add(cloudsStormSubTopology);
            i++;
        }
        cloudsStormMap.put("cloud_storm_vm", cloudsStormVMsList);
        cloudsStormMap.put("cloud_storm_subtopologies", cloudsStormSubTopologies);
        return cloudsStormMap;
    }

    protected CloudsStormVM getBestMatchingCloudStormVM(NodeTemplateMap vmMap, String provider) throws IOException, Exception {
        Double requestedNumOfCores = getHelper().getVMNumOfCores(vmMap);
        Double requestedMemSize = getHelper().getVMNMemSize(vmMap);
        String requestedOs = getHelper().getVMNOS(vmMap);
        Double requestedDiskSize = getHelper().getVMNDiskSize(vmMap);
        double[] requestedVector = convert2ArrayofDoubles(requestedNumOfCores, requestedMemSize, requestedDiskSize);
        double min = Double.MAX_VALUE;
        CloudsStormVM bestMatchingVM = null;
        List<CloudsStormVM> vmInfos = cloudStormDAO.findVmMetaInfoByProvider(CloudProviderEnum.fromValue(provider));
        for (CloudsStormVM vmInfo : vmInfos) {
            if (requestedOs.toLowerCase().equals(vmInfo.getOstype().toLowerCase())) {
                Double cloudsStormVMdiskSize;
                if (vmInfo.getDiskSize() == null) {
                    if (vmInfo.getExtraInfo() != null && vmInfo.getExtraInfo().containsKey("DiskSize")) {
                        int intSize = (int) vmInfo.getExtraInfo().get("DiskSize");
                        cloudsStormVMdiskSize = Double.valueOf(intSize);
                        vmInfo.setDiskSize(intSize);
                    } else {
                        cloudsStormVMdiskSize = 7.0;
                    }

                } else {
                    cloudsStormVMdiskSize = Double.valueOf(vmInfo.getDiskSize());
                }
                double[] aveliableVector = convert2ArrayofDoubles(Double.valueOf(vmInfo.getCPU()),
                        Double.valueOf(vmInfo.getMEM()),
                        cloudsStormVMdiskSize);
                EuclideanDistance dist = new EuclideanDistance();
                double res = dist.compute(requestedVector, aveliableVector);
                if (res < min) {
                    min = res;
                    bestMatchingVM = vmInfo;
                }
            }
        }
        if (bestMatchingVM != null && provider.equals("EC2")) {
            bestMatchingVM.setDiskSize(requestedDiskSize.intValue());
            bestMatchingVM.getExtraInfo().put("DiskSize", requestedDiskSize.intValue());
            bestMatchingVM.getExtraInfo().put("diskSize", requestedDiskSize.intValue());
        }
//        if (bestMatchingVM != null && bestMatchingVM.getDiskSize() == null
//                && bestMatchingVM.getExtraInfo() == null && !bestMatchingVM.getExtraInfo().containsKey("DiskSize")) {
//            bestMatchingVM.setDiskSize(requestedDiskSize.intValue());
//        }
        Logger.getLogger(CloudStormService.class.getName()).log(Level.FINE, "Found best matching VM: {0}", bestMatchingVM);
        return bestMatchingVM;
    }

    protected void writeCloudStormCredentialsFiles(String credentialsTempInputDirPath) throws ApiException, Exception {
        List<NodeTemplateMap> vmTopologiesMaps = getHelper().getVMTopologyTemplates();
        List<CloudCred> cloudStormCredentialList = new ArrayList<>();
        int i = 0;
        for (NodeTemplateMap vmTopologyMap : vmTopologiesMaps) {
            Credential toscaCredentials = getHelper().getCredentialsFromVMTopology(vmTopologyMap);
            CloudCred cloudStormCredential = new CloudCred();
            cloudStormCredential.setCloudProvider(toscaCredentials.getCloudProviderName());
            String credInfoFile = toscaCredentials.getCloudProviderName() + i + ".yml";
            cloudStormCredential.setCredInfoFile(credInfoFile);
            cloudStormCredentialList.add(cloudStormCredential);

            CredentialInfo cloudStormCredentialInfo = getCloudStormCredentialInfo(toscaCredentials, credentialsTempInputDirPath);
            objectMapper.writeValue(new File(credentialsTempInputDirPath + File.separator + toscaCredentials.getCloudProviderName() + i + ".yml"), cloudStormCredentialInfo);
            i++;
        }
        CloudCredentialDB cloudStormCredentials = new CloudCredentialDB();
        cloudStormCredentials.setCloudCreds(cloudStormCredentialList);
        objectMapper.writeValue(new File(credentialsTempInputDirPath + File.separator + "cred.yml"), cloudStormCredentials);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.INFO, "Wrote  cloudStorm credentials at : {0}{1}cred.yml", new Object[]{credentialsTempInputDirPath, File.separator});
    }

    protected CredentialInfo getCloudStormCredentialInfo(Credential toscaCredentials, String tmpPath) throws FileNotFoundException, IOException {
        CredentialInfo cloudStormCredentialInfo = new CredentialInfo();
        switch (toscaCredentials.getCloudProviderName().toLowerCase()) {
            case "exogeni":
                String base64Keystore = toscaCredentials.getKeys().get("keystore");
                Converter.decodeBase64BToFile(base64Keystore, tmpPath + File.separator + "user.jks");
                cloudStormCredentialInfo.setUserKeyName("user.jks");
                cloudStormCredentialInfo.setKeyAlias(toscaCredentials.getUser());
                cloudStormCredentialInfo.setKeyPassword(toscaCredentials.getToken());
                return cloudStormCredentialInfo;
            case "ec2":
                cloudStormCredentialInfo.setSecretKey(toscaCredentials.getToken());
                cloudStormCredentialInfo.setAccessKey(toscaCredentials.getKeys().get("aws_access_key_id"));
                return cloudStormCredentialInfo;

        }
        return null;
    }

    protected void writeCloudStormInfrasCodeFiles(String infrasCodeTempInputDirPath, List<CloudsStormSubTopology> cloudStormSubtopologies) throws ApiException, IOException {
        List<NodeTemplateMap> vmTopologiesMaps = getHelper().getVMTopologyTemplates();
        int i = 0;
        List<InfrasCode> infrasCodes = new ArrayList<>();
        for (NodeTemplateMap vmTopologyMap : vmTopologiesMaps) {
            Constants.NODE_STATES nodeCurrentState = getHelper().getNodeCurrentState(vmTopologyMap);
            Constants.NODE_STATES nodeDesiredState = getHelper().getNodeDesiredState(vmTopologyMap);
            //Can provision

            Map<String, Object> provisionInterface = getHelper().getProvisionerInterfaceFromVMTopology(vmTopologyMap);
            OpCode.OperationEnum operation = ToscaHelper.NodeDesiredState2CloudStormOperation(nodeDesiredState);
            Map<String, Object> inputs = (Map<String, Object>) provisionInterface.get(operation.toString().toLowerCase());
            inputs.put("object_type", cloudStormSubtopologies.get(i).getTopology());
            OpCode opCode = new OpCode();
            opCode.setLog(Boolean.FALSE);
            opCode.setObjectType(OpCode.ObjectTypeEnum.SUBTOPOLOGY);
            opCode.setObjects(cloudStormSubtopologies.get(i).getTopology());
            opCode.setOperation(operation);
            InfrasCode infrasCode = new InfrasCode();
            infrasCode.setCodeType(InfrasCode.CodeTypeEnum.SEQ);
            infrasCode.setOpCode(opCode);
            infrasCodes.add(infrasCode);

        }
        CloudsStormInfrasCode cloudsStormInfrasCode = new CloudsStormInfrasCode();
        cloudsStormInfrasCode.setMode(CloudsStormInfrasCode.ModeEnum.LOCAL);
        cloudsStormInfrasCode.setInfrasCodes(infrasCodes);
        File infrasCodeFile = new File(infrasCodeTempInputDirPath + File.separator + INFRASTUCTURE_CODE_FILE_NAME);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.INFO, "Wrote infrasCode file: {0}", infrasCodeFile.getAbsolutePath());
        objectMapper.writeValue(infrasCodeFile, cloudsStormInfrasCode);
    }

    protected void writeCloudStormProvidersDBFiles(String tempInputDirPath) throws IOException {
        File srcDir = new File(cloudStormDBPath);
        File destDir = new File(tempInputDirPath);
        FileUtils.copyDirectory(srcDir, destDir);
    }

    protected ToscaTemplate runCloudStorm(String tempInputDirPath, boolean dryRun) throws IOException, ApiException {
        String[] args = new String[]{"run", tempInputDirPath};
        File topTopologyFile = new File(tempInputDirPath + TOPOLOGY_RELATIVE_PATH
                + TOP_TOPOLOGY_FILE_NAME);
        if (!dryRun) {
            standalone.MainAsTool.main(args);
        } else {
            TopologyAnalysisMain tam = new TopologyAnalysisMain(topTopologyFile.getAbsolutePath());
            if (!tam.fullLoadWholeTopology()) {
                Logger.getLogger(CloudStormService.class.getName()).log(Level.FINE, "CloudStrom topology file at: {0} has errors", topTopologyFile.getAbsolutePath());
            }
        }

        CloudsStormTopTopology _top = objectMapper.readValue(topTopologyFile,
                CloudsStormTopTopology.class);

        List<CloudsStormSubTopology> subTopologies = _top.getTopologies();

        List<NodeTemplateMap> vmTopologiesMaps = getHelper().getVMTopologyTemplates();
        int i = 0;
        for (CloudsStormSubTopology subTopology : subTopologies) {

            setSSHKeysToVMAttributes(i, vmTopologiesMaps, subTopology, tempInputDirPath);

        }
        return toscaTemplate;
    }

    private double[] convert2ArrayofDoubles(Double numOfCores, Double memSize, Double diskSize) {
        double[] vector = new double[]{numOfCores, memSize, diskSize};
        return vector;
    }

    protected KeyPair getKeyPair() throws ApiException, TypeExeption, JSchException {
        KeyPair keyPair = null;
        List<NodeTemplateMap> vmTopologyTemplatesMap = getHelper().getVMTopologyTemplates();
        for (NodeTemplateMap nodeTemplateMap : vmTopologyTemplatesMap) {
            List<NodeTemplateMap> vmTemplatesMap = getHelper().getTemplateVMsForVMTopology(nodeTemplateMap);
            for (NodeTemplateMap vmMap : vmTemplatesMap) {
                Logger.getLogger(CloudStormService.class.getName()).log(Level.INFO, "Getting ssh keys for: {0}", vmMap.getName());
                keyPair = getHelper().getKeyPairsFromVM(vmMap.getNodeTemplate());
                break;
            }
        }
        return keyPair;
    }

    protected NodeTemplateMap addCloudStromArtifacts(NodeTemplateMap vmTopologyMap, String tempInputDirPath) throws IOException {
        Map<String, Object> artifacts = vmTopologyMap.getNodeTemplate().getArtifacts();
        if (artifacts == null) {
            artifacts = new HashMap<>();
        }
        Map<String, String> provisionedFiles = new HashMap<>();
        provisionedFiles.put("type", ENCODED_FILE_DATATYPE);
        File tempInputDirFile = new File(tempInputDirPath);
        String zipPath = (tempInputDirFile.getAbsolutePath() + CLOUD_STORM_FILES_ZIP_SUFIX);
        String sourceFolderPath = tempInputDirPath;
        Converter.zipFolder(sourceFolderPath, zipPath);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.FINE, "Created zip at: {0}", zipPath);

        String cloudStormZipFileContentsAsBase64 = Converter.encodeFileToBase64Binary(zipPath);
        provisionedFiles.put("file_contents", cloudStormZipFileContentsAsBase64);
        provisionedFiles.put("encoding", "base64");
        provisionedFiles.put("file_ext", "zip");
        artifacts.put("provisioned_files", provisionedFiles);
        vmTopologyMap.getNodeTemplate().setArtifacts(artifacts);
        Logger.getLogger(CloudStormService.class.getName()).log(Level.FINE, "Added zip artifacts in node: {0}", vmTopologyMap.getName());
        return vmTopologyMap;
    }

    private void setSSHKeysToVMAttributes(int i, List<NodeTemplateMap> vmTopologiesMaps, CloudsStormSubTopology subTopology, String tempInputDirPath) throws IOException, ApiException {
        NodeTemplateMap vmTopologyMap = vmTopologiesMaps.get(i);

        vmTopologyMap = addCloudStromArtifacts(vmTopologyMap, tempInputDirPath);

        getHelper().setNodeCurrentState(vmTopologyMap, cloudStormStatus2NodeState(subTopology.getStatus()));

        Credential rootKeyPairCredential = null;
        if (subTopology.getSshKeyPairId() != null) {
            String rootKeyPairFolder = tempInputDirPath + TOPOLOGY_RELATIVE_PATH
                    + File.separator + subTopology.getSshKeyPairId();

            rootKeyPairCredential = new Credential();
            rootKeyPairCredential.setProtocol("ssh");
            Map<String, String> rootKeys = new HashMap<>();
            rootKeys.put("private_key", Converter.encodeFileToBase64Binary(rootKeyPairFolder + File.separator + "id_rsa"));
            DirectoryScanner scanner = new DirectoryScanner();
            scanner.setIncludes(new String[]{"**/*.pub"});
            scanner.setBasedir(rootKeyPairFolder + File.separator);
            scanner.setCaseSensitive(false);
            scanner.scan();
            String[] fileNames = scanner.getIncludedFiles();

            rootKeys.put("public_key", Converter.encodeFileToBase64Binary(rootKeyPairFolder + File.separator + File.separator + fileNames[0]));
            rootKeyPairCredential.setKeys(rootKeys);
        }

        String userKyePairFolder = tempInputDirPath + TOPOLOGY_RELATIVE_PATH;
        Credential userKeyPairCredential = new Credential();
        userKeyPairCredential.setProtocol("ssh");
        Map<String, String> userKyes = new HashMap<>();
        userKyes.put("private_key", Converter.encodeFileToBase64Binary(userKyePairFolder + File.separator + "id_rsa"));
        userKyes.put("public_key", Converter.encodeFileToBase64Binary(userKyePairFolder + File.separator + "id_rsa.pub"));
        userKeyPairCredential.setKeys(userKyes);

        CloudsStormVMs cloudsStormVMs = objectMapper.readValue(new File(tempInputDirPath + TOPOLOGY_RELATIVE_PATH + File.separator + subTopology.getTopology() + ".yml"),
                CloudsStormVMs.class);
        List<CloudsStormVM> vms = cloudsStormVMs.getVms();
        List<NodeTemplateMap> vmTemplatesMap = getHelper().getTemplateVMsForVMTopology(vmTopologyMap);
        int j = 0;
        for (CloudsStormVM vm : vms) {
            NodeTemplateMap vmTemplateMap = vmTemplatesMap.get(j);
            Map<String, Object> vmAttributes = vmTemplateMap.getNodeTemplate().getAttributes();
            if (vmAttributes == null) {
                vmAttributes = new HashMap<>();
            }
            vmAttributes.put("private_ip", vm.getSelfEthAddresses());
            vmAttributes.put("public_ip", vm.getPublicAddress());
            if (j > 0) {
                vmAttributes.put("role", "worker");
            } else {
                vmAttributes.put("role", "master");
            }
            vmAttributes.put("node_type", vm.getNodeType());
            vmAttributes.put("host_name", vm.getName());
            if (rootKeyPairCredential != null) {
                vmAttributes.put("root_key_pair", rootKeyPairCredential);
            }
            vmAttributes.put("user_key_pair", userKeyPairCredential);
            vmTemplateMap.getNodeTemplate().setAttributes(vmAttributes);
            toscaTemplate = getHelper().setNodeInToscaTemplate(toscaTemplate, vmTemplateMap);
            j++;
        }
        toscaTemplate = getHelper().setNodeInToscaTemplate(toscaTemplate, vmTopologyMap);
        i++;
    }

    private void initCloudStormFiles(String tempInputDirPath) throws FileNotFoundException, IOException, Exception {

        String topologyTempInputDirPath = tempInputDirPath + TOPOLOGY_RELATIVE_PATH;

        File topologyTempInputDir = new File(topologyTempInputDirPath);
        if (!(topologyTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + topologyTempInputDir.getAbsolutePath());
        }
        Map<String, Object> subTopologiesAndVMs = writeCloudStormTopologyFiles(topologyTempInputDirPath);

        String credentialsTempInputDirPath = tempInputDirPath + File.separator + INFS_FOLDER_NAME + File.separator + UC_FOLDER_NAME;
        File credentialsTempInputDir = new File(credentialsTempInputDirPath);
        if (!(credentialsTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + credentialsTempInputDir.getAbsolutePath());
        }
        writeCloudStormCredentialsFiles(credentialsTempInputDirPath);

        String providersDBTempInputDirPath = tempInputDirPath + File.separator + INFS_FOLDER_NAME + File.separator + UD_FOLDER_NAME;
        File providersDBTempInputDir = new File(providersDBTempInputDirPath);
        if (!(providersDBTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + providersDBTempInputDir.getAbsolutePath());
        }
        writeCloudStormProvidersDBFiles(providersDBTempInputDirPath);

        String infrasCodeTempInputDirPath = tempInputDirPath + File.separator + APP_FOLDER_NAME;
        File infrasCodeTempInputDir = new File(infrasCodeTempInputDirPath);
        if (!(infrasCodeTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + topologyTempInputDir.getAbsolutePath());
        }
        List<CloudsStormSubTopology> cloudStormSubtopologies = (List<CloudsStormSubTopology>) subTopologiesAndVMs.get("cloud_storm_subtopologies");
        writeCloudStormInfrasCodeFiles(infrasCodeTempInputDirPath, cloudStormSubtopologies);
    }

}
