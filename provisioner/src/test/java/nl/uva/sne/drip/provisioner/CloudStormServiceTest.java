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
import com.jcraft.jsch.KeyPair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import static nl.uva.sne.drip.commons.utils.Constants.*;
import nl.uva.sne.drip.commons.utils.Converter;
import nl.uva.sne.drip.commons.utils.ToscaHelper;
import nl.uva.sne.drip.model.Message;
import nl.uva.sne.drip.model.NodeTemplateMap;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormInfrasCode;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormSubTopology;
import nl.uva.sne.drip.model.cloud.storm.CloudsStormTopTopology;
import nl.uva.sne.drip.model.cloud.storm.InfrasCode;
import nl.uva.sne.drip.model.cloud.storm.OpCode;
import static nl.uva.sne.drip.provisioner.CloudStormService.APP_FOLDER_NAME;
import static nl.uva.sne.drip.provisioner.CloudStormService.INFRASTUCTURE_CODE_FILE_NAME;
import static nl.uva.sne.drip.provisioner.CloudStormService.INFS_FOLDER_NAME;
import static nl.uva.sne.drip.provisioner.CloudStormService.TOPOLOGY_RELATIVE_PATH;
import static nl.uva.sne.drip.provisioner.CloudStormService.TOP_TOPOLOGY_FILE_NAME;
import static nl.uva.sne.drip.provisioner.CloudStormService.UC_FOLDER_NAME;
import static nl.uva.sne.drip.provisioner.CloudStormService.UD_FOLDER_NAME;
import nl.uva.sne.drip.sure.tosca.client.ApiException;
import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.logging.Logger;

/**
 *
 * @author S. Koulouzis
 */
public class CloudStormServiceTest {

    private static final String messageExampleDeleteRequestFilePath = ".." + File.separator + "example_messages" + File.separator + "message_delete_request.json";
    private static final String messageExampleProvisioneRequestFilePath = ".." + File.separator + "example_messages" + File.separator + "message_provision_request.json";
    private final ObjectMapper objectMapper;
    private String tempInputDirPath;
    private String topologyTempInputDirPath;
    private String sureToscaBasePath;
    private File tempInputDir;
    private File topologyTempInputDir;
    private String infrasCodeTempInputDirPath;
    private String credentialsTempInputDirPath;
    private String providersDBTempInputDirPath;

    public CloudStormServiceTest() {
        this.objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException, JsonProcessingException, ApiException {
        String[] argv = new String[0];
        RPCServer.init(argv);
        sureToscaBasePath = RPCServer.getProp().getProperty("sure-tosca.base.path");
        initPaths();

    }

    @After
    public void tearDown() {
    }

    private void initPaths() throws FileNotFoundException {
        tempInputDirPath = System.getProperty("java.io.tmpdir") + File.separator + "Input-" + Long.toString(System.nanoTime()) + File.separator;
        tempInputDir = new File(tempInputDirPath);
        if (!(tempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + tempInputDir.getAbsolutePath());
        }
        topologyTempInputDirPath = tempInputDirPath + TOPOLOGY_RELATIVE_PATH;

        topologyTempInputDir = new File(topologyTempInputDirPath);
        if (!(topologyTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + topologyTempInputDir.getAbsolutePath());
        }

        credentialsTempInputDirPath = tempInputDirPath + File.separator + INFS_FOLDER_NAME + File.separator + UC_FOLDER_NAME;
        File credentialsTempInputDir = new File(credentialsTempInputDirPath);
        if (!(credentialsTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + credentialsTempInputDir.getAbsolutePath());
        }

        providersDBTempInputDirPath = tempInputDirPath + File.separator + INFS_FOLDER_NAME + File.separator + UD_FOLDER_NAME;
        File providersDBTempInputDir = new File(providersDBTempInputDirPath);
        if (!(providersDBTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + providersDBTempInputDir.getAbsolutePath());
        }

        infrasCodeTempInputDirPath = tempInputDirPath + File.separator + APP_FOLDER_NAME;
        File infrasCodeTempInputDir = new File(infrasCodeTempInputDirPath);
        if (!(infrasCodeTempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + topologyTempInputDir.getAbsolutePath());
        }
    }

    private CloudStormService getService(String messagefilePath) throws IOException, JsonProcessingException, ApiException {
        Logger.getLogger(CloudStormServiceTest.class.getName()).log(Level.INFO, "Reterning  CloudStormService for file: {0}", new File(messagefilePath).getAbsolutePath());
        Message message = objectMapper.readValue(new File(messagefilePath), Message.class);
        return new CloudStormService(RPCServer.getProp(), message.getToscaTemplate());
    }

    /**
     * Test of execute method, of class CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("execute");
            CloudStormService instance = getService(messageExampleProvisioneRequestFilePath);
            boolean dryRun = true;
            instance.execute(dryRun);
        }
    }

    /**
     * Test of writeCloudStormTopologyFiles method, of class CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteCloudStormTopologyFiles() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("writeCloudStormTopologyFiles");
            CloudStormService instance = getService(messageExampleProvisioneRequestFilePath);
            Map<String, Object> result = instance.writeCloudStormTopologyFiles(tempInputDirPath);
            assertNotNull(result);
        }

    }

    /**
     * Test of buildSSHKeyPair method, of class CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBuildSSHKeyPair() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("buildSSHKeyPair");
            CloudStormService instance = getService(messageExampleProvisioneRequestFilePath);
            initPaths();
            String result = instance.buildSSHKeyPair(tempInputDirPath, null);
            assertNotNull(result);
            String userPublicKeyName = "id_rsa.pub";
            String userPrivateName = FilenameUtils.removeExtension(userPublicKeyName);
            assertTrue(new File(tempInputDirPath + File.separator + userPrivateName).exists());
            assertTrue(new File(tempInputDirPath + File.separator + userPublicKeyName).exists());

            instance = getService(messageExampleDeleteRequestFilePath);
            initPaths();
            result = instance.buildSSHKeyPair(tempInputDirPath, null);
            assertNotNull(result);
            userPublicKeyName = "id_rsa.pub";
            userPrivateName = FilenameUtils.removeExtension(userPublicKeyName);
            assertTrue(new File(tempInputDirPath + File.separator + userPrivateName).exists());
            assertTrue(new File(tempInputDirPath + File.separator + userPublicKeyName).exists());
        }
    }

    /**
     * Test of writeCloudStormInfrasCodeFiles method, of class
     * CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteCloudStormInfrasCodeFiles() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("writeCloudStormInfrasCodeFiles");

            testWriteCloudStormInfrasFiles(messageExampleProvisioneRequestFilePath, CloudsStormSubTopology.StatusEnum.FRESH, OpCode.OperationEnum.PROVISION);
            testWriteCloudStormInfrasFiles(messageExampleDeleteRequestFilePath, CloudsStormSubTopology.StatusEnum.RUNNING, OpCode.OperationEnum.DELETE);
        }

    }

    /**
     * Test of getKeyPair method, of class CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetKeyPair() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("getKeyPair");
            CloudStormService instance = getService(messageExampleProvisioneRequestFilePath);
            KeyPair result = instance.getKeyPair();
            assertNull(result);

            instance = getService(messageExampleDeleteRequestFilePath);
            result = instance.getKeyPair();
            assertNotNull(result);
        }

    }

    private void testWriteCloudStormInfrasFiles(String path, CloudsStormSubTopology.StatusEnum status, OpCode.OperationEnum opCode) throws IOException, JsonProcessingException, ApiException, Exception {
        CloudStormService instance = getService(path);
        initPaths();
        Map<String, Object> subTopologiesAndVMs = instance.writeCloudStormTopologyFiles(topologyTempInputDirPath);
        assertNotNull(subTopologiesAndVMs);
        File topTopologyFile = new File(topologyTempInputDirPath + File.separator + TOP_TOPOLOGY_FILE_NAME);
        assertTrue(topTopologyFile.exists());
        CloudsStormTopTopology _top = objectMapper.readValue(new File(tempInputDirPath + TOPOLOGY_RELATIVE_PATH
                + TOP_TOPOLOGY_FILE_NAME),
                CloudsStormTopTopology.class);

        for (CloudsStormSubTopology cloudsStormSubTopology : _top.getTopologies()) {
            assertEquals(status, cloudsStormSubTopology.getStatus());
        }

        List<CloudsStormSubTopology> cloudStormSubtopologies = (List<CloudsStormSubTopology>) subTopologiesAndVMs.get("cloud_storm_subtopologies");
        instance.writeCloudStormInfrasCodeFiles(infrasCodeTempInputDirPath, cloudStormSubtopologies);
        File infrasCodeFile = new File(infrasCodeTempInputDirPath + File.separator + INFRASTUCTURE_CODE_FILE_NAME);
        assertTrue(infrasCodeFile.exists());
        CloudsStormInfrasCode cloudsStormInfrasCode = objectMapper.readValue(infrasCodeFile, CloudsStormInfrasCode.class);

        for (InfrasCode code : cloudsStormInfrasCode.getInfrasCodes()) {
            assertEquals(opCode, code.getOpCode().getOperation());
        }
    }

    /**
     * Test of addCloudStromArtifacts method, of class CloudStormService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddCloudStromArtifacts() throws Exception {
        if (ToscaHelper.isServiceUp(sureToscaBasePath)) {
            System.out.println("addCloudStromArtifacts");
            testWriteCloudStormInfrasFiles(messageExampleProvisioneRequestFilePath, CloudsStormSubTopology.StatusEnum.FRESH, OpCode.OperationEnum.PROVISION);
            CloudStormService instance = getService(messageExampleProvisioneRequestFilePath);
            List<NodeTemplateMap> vmTopologiesMaps = instance.getHelper().getVMTopologyTemplates();
            for (NodeTemplateMap vmTopologyMap : vmTopologiesMaps) {
                vmTopologyMap = instance.addCloudStromArtifacts(vmTopologyMap, tempInputDirPath);

                File tempInputDirFile = new File(tempInputDirPath);
                String zipPath = (tempInputDirFile.getAbsolutePath() + CLOUD_STORM_FILES_ZIP_SUFIX);

                File zipFile = new File(zipPath);
                assertTrue(zipFile.exists());
                assertTrue(zipFile.length() > 1);
                String contentType = Files.probeContentType(Paths.get(zipFile.getAbsolutePath()));
                assertEquals("application/zip", contentType);
                Map<String, Object> artifacts = vmTopologyMap.getNodeTemplate().getArtifacts();
                assertNotNull(artifacts);
                assertTrue(artifacts.containsKey("provisioned_files"));
                Map<String, String> provisionedFiles = (Map<String, String>) artifacts.get("provisioned_files");
                assertNotNull(provisionedFiles);

                assertTrue(provisionedFiles.containsKey("file_contents"));
                String cloudStormZipFileContentsAsBase64 = provisionedFiles.get("file_contents");
                String testZipPath = tempInputDirPath + File.separator + "test.zip";

                Converter.decodeBase64BToFile(cloudStormZipFileContentsAsBase64, testZipPath);
                contentType = Files.probeContentType(Paths.get(testZipPath));
                assertEquals("application/zip", contentType);
                assertEquals(Converter.getFileMD5(zipFile.getAbsolutePath()), Converter.getFileMD5(testZipPath));

                assertTrue(provisionedFiles.containsKey("encoding"));
                assertTrue(provisionedFiles.containsKey("file_ext"));
            }
        }
    }
}
