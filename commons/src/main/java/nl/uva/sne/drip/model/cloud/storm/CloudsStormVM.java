package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CloudsStormVM
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-12T18:26:54.530Z")

public class CloudsStormVM {

    /**
     * @return the iOPS
     */
    public Integer getiOPS() {
        return iOPS;
    }

    /**
     * @param iOPS the iOPS to set
     */
    public void setiOPS(Integer iOPS) {
        this.iOPS = iOPS;
    }

    /**
     * @return the vpcId
     */
    public String getVpcId() {
        return vpcId;
    }

    /**
     * @param vpcId the vpcId to set
     */
    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    /**
     * @return the subnetId
     */
    public String getSubnetId() {
        return subnetId;
    }

    /**
     * @param subnetId the subnetId to set
     */
    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    /**
     * @return the securityGroupId
     */
    public String getSecurityGroupId() {
        return securityGroupId;
    }

    /**
     * @param securityGroupId the securityGroupId to set
     */
    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    /**
     * @return the instanceId
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId the instanceId to set
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * @return the volumeId
     */
    public Object getVolumeId() {
        return volumeId;
    }

    /**
     * @param volumeId the volumeId to set
     */
    public void setVolumeId(Object volumeId) {
        this.volumeId = volumeId;
    }

    /**
     * @return the routeTableId
     */
    public String getRouteTableId() {
        return routeTableId;
    }

    /**
     * @param routeTableId the routeTableId to set
     */
    public void setRouteTableId(String routeTableId) {
        this.routeTableId = routeTableId;
    }

    /**
     * @return the internetGatewayId
     */
    public String getInternetGatewayId() {
        return internetGatewayId;
    }

    /**
     * @param internetGatewayId the internetGatewayId to set
     */
    public void setInternetGatewayId(String internetGatewayId) {
        this.internetGatewayId = internetGatewayId;
    }

    /**
     * @return the diskSize
     */
    public Integer getDiskSize() {
        return diskSize;
    }

    /**
     * @param diskSize the diskSize to set
     */
    public void setDiskSize(Integer diskSize) {
        this.diskSize = diskSize;
    }

    /**
     * @return the vEngineClass
     */
    public String getvEngineClass() {
        return vEngineClass;
    }

    /**
     * @param vEngineClass the vEngineClass to set
     */
    public void setvEngineClass(String vEngineClass) {
        this.vEngineClass = vEngineClass;
    }

    /**
     * @return the vNFType
     */
    public String getvNFType() {
        return vNFType;
    }

    /**
     * @param vNFType the vNFType to set
     */
    public void setvNFType(String vNFType) {
        this.vNFType = vNFType;
    }

    /**
     * @return the scaledFrom
     */
    public String getScaledFrom() {
        return scaledFrom;
    }

    /**
     * @param scaledFrom the scaledFrom to set
     */
    public void setScaledFrom(String scaledFrom) {
        this.scaledFrom = scaledFrom;
    }

    /**
     * @return the fake
     */
    public String getFake() {
        return fake;
    }

    /**
     * @param fake the fake to set
     */
    public void setFake(String fake) {
        this.fake = fake;
    }

    /**
     * @return the OS_URL
     */
    public String getOS_URL() {
        return OS_URL;
    }

    /**
     * @param OS_URL the OS_URL to set
     */
    public void setOS_URL(String OS_URL) {
        this.OS_URL = OS_URL;
    }

    /**
     * @return the OS_GUID
     */
    public String getOS_GUID() {
        return OS_GUID;
    }

    /**
     * @param OS_GUID the OS_GUID to set
     */
    public void setOS_GUID(String OS_GUID) {
        this.OS_GUID = OS_GUID;
    }

    /**
     * @return the VEngineClass
     */
    public String getVEngineClass() {
        return VEngineClass;
    }

    /**
     * @param VEngineClass the VEngineClass to set
     */
    public void setVEngineClass(String VEngineClass) {
        this.VEngineClass = VEngineClass;
    }

    /**
     * @return the selfEthAddresses
     */
    public String getSelfEthAddresses() {
        return selfEthAddresses;
    }

    /**
     * @param selfEthAddresses the selfEthAddresses to set
     */
    public void setSelfEthAddresses(String selfEthAddresses) {
        this.selfEthAddresses = selfEthAddresses;
    }

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("nodeType")
    @JsonAlias({"type"})
    private String nodeType = null;

    @JsonProperty("VEngineClass")
    private String VEngineClass = null;

    @JsonProperty("OStype")
    @JsonAlias({"os", "OS"})
    private String ostype = null;

    @JsonProperty("script")
    private String script = null;

    @JsonProperty("publicAddress")
    private String publicAddress = null;

    @JsonProperty("extraInfo")
    @Valid
    private Map<String, Object> extraInfo = null;

    @JsonProperty("CPU")
    @JsonAlias({"cpu"})
    private String CPU = null;

    @JsonProperty("MEM")
    @JsonAlias({"Mem"})
    private String MEM = null;

    @JsonProperty("VMType")
    @JsonAlias({"type"})
    private String vmType = null;

    @JsonProperty("Price")
    private String price = null;

    @JsonProperty("DefaultSSHAccount")
    @JsonAlias({"defaultSSHAccount"})
    private String defaultSSHAccount = null;

    @JsonProperty("availability")
    private String availability = null;

    @JsonProperty("selfEthAddresses")
    private String selfEthAddresses = null;

    @JsonProperty("VEngineClass")
    private String vEngineClass = null;

    @JsonProperty("VNFType")
    private String vNFType = null;

    @JsonProperty("scaledFrom")
    private String scaledFrom = null;

    @JsonProperty("fake")
    private String fake = null;

    @JsonProperty("OS_URL")
    private String OS_URL = null;

    @JsonProperty("OS_GUID")
    private String OS_GUID = null;

    @JsonProperty("diskSize")
    @JsonAlias({"DiskSize"})
    private Integer diskSize;

    public CloudsStormVM name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     *
     */
    @ApiModelProperty(value = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloudsStormVM nodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    /**
     * Get nodeType
     *
     * @return nodeType
     *
     */
    @ApiModelProperty(value = "")

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public CloudsStormVM ostype(String ostype) {
        this.ostype = ostype;
        return this;
    }

    /**
     * Get ostype
     *
     * @return ostype
     *
     */
    @ApiModelProperty(value = "")

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    public CloudsStormVM script(String script) {
        this.script = script;
        return this;
    }

    /**
     * Get script
     *
     * @return script
     *
     */
    @ApiModelProperty(value = "")

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public CloudsStormVM publicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
        return this;
    }

    /**
     * Get publicAddress
     *
     * @return publicAddress
     *
     */
    @ApiModelProperty(value = "")

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

//    public CloudsStormVM type(String type) {
//        this.type = type;
//        return this;
//    }
//
//    /**
//     * Get type
//     *
//     * @return type
//     *
//     */
//    @ApiModelProperty(value = "")
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
    public CloudsStormVM extraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }

    public CloudsStormVM putExtraInfoItem(String key, Object extraInfoItem) {
        if (this.extraInfo == null) {
            this.extraInfo = new HashMap<String, Object>();
        }
        this.extraInfo.put(key, extraInfoItem);
        return this;
    }

    /**
     * Get extraInfo
     *
     * @return extraInfo
     *
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

//    public CloudsStormVM OS(String OS) {
//        this.OS = OS;
//        return this;
//    }
//
//    /**
//     * Get OS
//     *
//     * @return OS
//     *
//     */
//    @ApiModelProperty(value = "")
//
//    public String getOS() {
//        return OS;
//    }
//
//    public void setOS(String OS) {
//        this.OS = OS;
//    }
    public CloudsStormVM CPU(String CPU) {
        this.CPU = CPU;
        return this;
    }

    /**
     * Get CPU
     *
     * @return CPU
     *
     */
    @ApiModelProperty(value = "")

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public CloudsStormVM MEM(String MEM) {
        this.MEM = MEM;
        return this;
    }

    /**
     * Get MEM
     *
     * @return MEM
     *
     */
    @ApiModelProperty(value = "")

    public String getMEM() {
        return MEM;
    }

    public void setMEM(String MEM) {
        this.MEM = MEM;
    }

    public CloudsStormVM vmType(String vmType) {
        this.vmType = vmType;
        return this;
    }

    /**
     * Get vmType
     *
     * @return vmType
     *
     */
    @ApiModelProperty(value = "")

    public String getVmType() {
        return vmType;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public CloudsStormVM price(String price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
     * @return price
     *
     */
    @ApiModelProperty(value = "")

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CloudsStormVM defaultSSHAccount(String defaultSSHAccount) {
        this.defaultSSHAccount = defaultSSHAccount;
        return this;
    }

    /**
     * Get defaultSSHAccount
     *
     * @return defaultSSHAccount
     *
     */
    @ApiModelProperty(value = "")

    public String getDefaultSSHAccount() {
        return defaultSSHAccount;
    }

    public void setDefaultSSHAccount(String defaultSSHAccount) {
        this.defaultSSHAccount = defaultSSHAccount;
    }

    public CloudsStormVM availability(String availability) {
        this.availability = availability;
        return this;
    }

    /**
     * Get availability
     *
     * @return availability
     *
     */
    @ApiModelProperty(value = "")

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormVM cloudsStormVM = (CloudsStormVM) o;
        return Objects.equals(this.name, cloudsStormVM.name)
                && Objects.equals(this.nodeType, cloudsStormVM.nodeType)
                && Objects.equals(this.ostype, cloudsStormVM.ostype)
                && Objects.equals(this.script, cloudsStormVM.script)
                && Objects.equals(this.publicAddress, cloudsStormVM.publicAddress)
                //                && Objects.equals(this.type, cloudsStormVM.type)
                && Objects.equals(this.extraInfo, cloudsStormVM.extraInfo)
                //                && Objects.equals(this.OS, cloudsStormVM.OS)
                && Objects.equals(this.CPU, cloudsStormVM.CPU)
                && Objects.equals(this.MEM, cloudsStormVM.MEM)
                && Objects.equals(this.vmType, cloudsStormVM.vmType)
                && Objects.equals(this.price, cloudsStormVM.price)
                && Objects.equals(this.defaultSSHAccount, cloudsStormVM.defaultSSHAccount)
                && Objects.equals(this.availability, cloudsStormVM.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nodeType, ostype, script, publicAddress, extraInfo, CPU, MEM, vmType, price, defaultSSHAccount, availability);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormVM {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    nodeType: ").append(toIndentedString(nodeType)).append("\n");
        sb.append("    ostype: ").append(toIndentedString(ostype)).append("\n");
        sb.append("    script: ").append(toIndentedString(script)).append("\n");
        sb.append("    publicAddress: ").append(toIndentedString(publicAddress)).append("\n");
//        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    extraInfo: ").append(toIndentedString(extraInfo)).append("\n");
//        sb.append("    OS: ").append(toIndentedString(OS)).append("\n");
        sb.append("    CPU: ").append(toIndentedString(CPU)).append("\n");
        sb.append("    MEM: ").append(toIndentedString(MEM)).append("\n");
        sb.append("    vmType: ").append(toIndentedString(vmType)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    defaultSSHAccount: ").append(toIndentedString(defaultSSHAccount)).append("\n");
        sb.append("    availability: ").append(toIndentedString(availability)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @JsonProperty("IOPS")
    private Integer iOPS;
    @JsonProperty("vpcId")
    private String vpcId;
    @JsonProperty("subnetId")
    private String subnetId;
    @JsonProperty("securityGroupId")
    private String securityGroupId;
    @JsonProperty("instanceId")
    private String instanceId;
    @JsonProperty("volumeId")
    private Object volumeId;
    @JsonProperty("routeTableId")
    private String routeTableId;
    @JsonProperty("internetGatewayId")
    private String internetGatewayId;

}
