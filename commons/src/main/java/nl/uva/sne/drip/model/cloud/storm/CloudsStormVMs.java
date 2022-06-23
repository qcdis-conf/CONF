package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * CloudsStormVMs
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-12T18:26:54.530Z")

public class CloudsStormVMs {

    @JsonProperty("extraInfo")
    @Valid
    private Map<String, Object> extraInfo = null;
    @JsonProperty("VMs")
    @Valid
    private List<CloudsStormVM> vms = null;
    @JsonProperty("WhetherCreateVPC")
    @Valid
    private Boolean whetherCreateVPC;
    @JsonProperty("SEngineClass")
    @Valid
    private String sEngineClass = null;
    @JsonProperty("sliceName")
    @Valid
    private String sliceName = null;
    @JsonProperty("duration")
    @Valid
    private Float duration = null;

    /**
     * @return the whetherCreateVPC
     */
    public Boolean getWhetherCreateVPC() {
        return whetherCreateVPC;
    }

    /**
     * @param whetherCreateVPC the whetherCreateVPC to set
     */
    public void setWhetherCreateVPC(Boolean whetherCreateVPC) {
        this.whetherCreateVPC = whetherCreateVPC;
    }

    /**
     * @return the sliceName
     */
    public String getSliceName() {
        return sliceName;
    }

    /**
     * @param sliceName the sliceName to set
     */
    public void setSliceName(String sliceName) {
        this.sliceName = sliceName;
    }

    /**
     * @return the duration
     */
    public Float getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Float duration) {
        this.duration = duration;
    }

    /**
     * @return the extraInfo
     */
    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    /**
     * @param extraInfo the extraInfo to set
     */
    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     * @return the sEngineClass
     */
    public String getsEngineClass() {
        return sEngineClass;
    }

    /**
     * @param sEngineClass the sEngineClass to set
     */
    public void setsEngineClass(String sEngineClass) {
        this.sEngineClass = sEngineClass;
    }

    public CloudsStormVMs vms(List<CloudsStormVM> vms) {
        this.vms = vms;
        return this;
    }

    public CloudsStormVMs addVmsItem(CloudsStormVM vmsItem) {
        if (this.vms == null) {
            this.vms = new ArrayList<CloudsStormVM>();
        }
        this.vms.add(vmsItem);
        return this;
    }

    /**
     * Get vms
     *
     * @return vms
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudsStormVM> getVms() {
        return vms;
    }

    public void setVms(List<CloudsStormVM> vms) {
        this.vms = vms;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormVMs cloudsStormVMs = (CloudsStormVMs) o;
        return Objects.equals(this.vms, cloudsStormVMs.vms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vms);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormVMs {\n");

        sb.append("    vms: ").append(toIndentedString(vms)).append("\n");
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
}
