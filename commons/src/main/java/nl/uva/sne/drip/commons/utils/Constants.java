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

/**
 *
 * @author S. Koulouzis
 */
public class Constants {

    public static final String VM_CAPABILITY = "tosca.capabilities.QC.VM";
    public static final String VM_TYPE = "tosca.nodes.QC.VM.Compute";
    public static final String VM_NUM_OF_CORES = "num_cores";
    public static final String MEM_SIZE = "mem_size";
    public static final String DISK_SIZE = "disk_size";
    public static final String VM_OS = "os";
    public static final String VM_TOPOLOGY = "tosca.nodes.QC.VM.topology";
    public static final String APPLICATION_TYPE = "tosca.nodes.QC.Application";
    public static final String CLOUD_STORM_INTERFACE = "tosca.interfaces.QC.CloudsStorm";
    public static final String ENCODED_FILE_DATATYPE = "tosca.datatypes.QC.encodedFile";
    public static final String CLOUD_STORM_FILES_ZIP_SUFIX = "cloudStromFiles.zip";

    public static enum NODE_STATES {
        DELETED, STARTED, STOPPED, H_SCALED, V_SCALED, CONFIGURED, RUNNING, FAILED
    }

}
