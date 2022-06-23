package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * CloudsStormSubTopology
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-12T17:21:29.940Z")

public class CloudsStormSubTopology {

    @JsonProperty("topology")
    private String topology = null;

    @JsonProperty("cloudProvider")
    private String cloudProvider = null;

    @JsonProperty("domain")
    private String domain = null;
    @JsonProperty("status")
    private StatusEnum status = null;
    @JsonProperty("logsInfo")
    @Valid
    private Map<String, Object> logsInfo = null;
    @JsonProperty("subTopologyClass")
    private String subTopologyClass = null;
    @JsonProperty("sshKeyPairId")
    private String sshKeyPairId = null;
    @JsonProperty("scaledFrom")
    @Valid
    private Map<String, Object> scaledFrom = null;

    public CloudsStormSubTopology topology(String topology) {
        this.topology = topology;
        return this;
    }

    /**
     * Get topology
     *
     * @return topology
     */
    @ApiModelProperty(value = "")

    public String getTopology() {
        return topology;
    }

    public void setTopology(String topology) {
        this.topology = topology;
    }

    public CloudsStormSubTopology cloudProvider(String cloudProvider) {
        this.cloudProvider = cloudProvider;
        return this;
    }

    /**
     * Get cloudProvider
     *
     * @return cloudProvider
     */
    @ApiModelProperty(value = "")

    public String getCloudProvider() {
        return cloudProvider;
    }

    public void setCloudProvider(String cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    public CloudsStormSubTopology domain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Get domain
     *
     * @return domain
     */
    @ApiModelProperty(value = "")

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public CloudsStormSubTopology status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @ApiModelProperty(value = "")

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public CloudsStormSubTopology logsInfo(Map<String, Object> logsInfo) {
        this.logsInfo = logsInfo;
        return this;
    }

    public CloudsStormSubTopology putLogsInfoItem(String key, Object logsInfoItem) {
        if (this.logsInfo == null) {
            this.logsInfo = new HashMap<String, Object>();
        }
        this.logsInfo.put(key, logsInfoItem);
        return this;
    }

    /**
     * Get logsInfo
     *
     * @return logsInfo
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getLogsInfo() {
        return logsInfo;
    }

    public void setLogsInfo(Map<String, Object> logsInfo) {
        this.logsInfo = logsInfo;
    }

    public CloudsStormSubTopology subTopologyClass(String subTopologyClass) {
        this.subTopologyClass = subTopologyClass;
        return this;
    }

    /**
     * Get subTopologyClass
     *
     * @return subTopologyClass
     */
    @ApiModelProperty(value = "")

    public String getSubTopologyClass() {
        return subTopologyClass;
    }

    public void setSubTopologyClass(String subTopologyClass) {
        this.subTopologyClass = subTopologyClass;
    }

    public CloudsStormSubTopology sshKeyPairId(String sshKeyPairId) {
        this.sshKeyPairId = sshKeyPairId;
        return this;
    }

    /**
     * Get sshKeyPairId
     *
     * @return sshKeyPairId
     */
    @ApiModelProperty(value = "")

    public String getSshKeyPairId() {
        return sshKeyPairId;
    }

    public void setSshKeyPairId(String sshKeyPairId) {
        this.sshKeyPairId = sshKeyPairId;
    }

    public CloudsStormSubTopology scaledFrom(Map<String, Object> scaledFrom) {
        this.scaledFrom = scaledFrom;
        return this;
    }

    public CloudsStormSubTopology putScaledFromItem(String key, Object scaledFromItem) {
        if (this.scaledFrom == null) {
            this.scaledFrom = new HashMap<String, Object>();
        }
        this.scaledFrom.put(key, scaledFromItem);
        return this;
    }

    /**
     * Get scaledFrom
     *
     * @return scaledFrom
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getScaledFrom() {
        return scaledFrom;
    }

    public void setScaledFrom(Map<String, Object> scaledFrom) {
        this.scaledFrom = scaledFrom;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormSubTopology cloudsStormSubTopology = (CloudsStormSubTopology) o;
        return Objects.equals(this.topology, cloudsStormSubTopology.topology)
                && Objects.equals(this.cloudProvider, cloudsStormSubTopology.cloudProvider)
                && Objects.equals(this.domain, cloudsStormSubTopology.domain)
                && Objects.equals(this.status, cloudsStormSubTopology.status)
                && Objects.equals(this.logsInfo, cloudsStormSubTopology.logsInfo)
                && Objects.equals(this.subTopologyClass, cloudsStormSubTopology.subTopologyClass)
                && Objects.equals(this.sshKeyPairId, cloudsStormSubTopology.sshKeyPairId)
                && Objects.equals(this.scaledFrom, cloudsStormSubTopology.scaledFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topology, cloudProvider, domain, status, logsInfo, subTopologyClass, sshKeyPairId, scaledFrom);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormSubTopology {\n");

        sb.append("    topology: ").append(toIndentedString(topology)).append("\n");
        sb.append("    cloudProvider: ").append(toIndentedString(cloudProvider)).append("\n");
        sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    logsInfo: ").append(toIndentedString(logsInfo)).append("\n");
        sb.append("    subTopologyClass: ").append(toIndentedString(subTopologyClass)).append("\n");
        sb.append("    sshKeyPairId: ").append(toIndentedString(sshKeyPairId)).append("\n");
        sb.append("    scaledFrom: ").append(toIndentedString(scaledFrom)).append("\n");
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

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        FRESH("fresh"),
        RUNNING("running"),
        DELETED("deleted"),
        FAILED("failed"),
        STOPPED("stopped");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
