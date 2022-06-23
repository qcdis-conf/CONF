package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * CloudsStormTopTopology
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-11T15:13:55.016Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CloudsStormTopTopology {

    @JsonProperty("userName")
    private String userName = null;
    @JsonProperty("publicKeyPath")
    private String publicKeyPath = null;
    @JsonProperty("topologies")
    @Valid
    private List<CloudsStormSubTopology> topologies = null;
    @JsonProperty("subnets")
    @Valid
    private List<CloudsStormSubnets> subnets = null;
    @JsonProperty("connections")
    @Valid
    private Map<String, Object> connections = null;

    /**
     * @return the connections
     */
    public Map<String, Object> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(Map<String, Object> connections) {
        this.connections = connections;
    }

    public CloudsStormTopTopology userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Get userName
     *
     * @return userName
     */
    @ApiModelProperty(value = "")

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CloudsStormTopTopology publicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
        return this;
    }

    /**
     * Get publicKeyPath
     *
     * @return publicKeyPath
     */
    @ApiModelProperty(value = "")

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public CloudsStormTopTopology topologies(List<CloudsStormSubTopology> topologies) {
        this.topologies = topologies;
        return this;
    }

    public CloudsStormTopTopology addTopologiesItem(CloudsStormSubTopology topologiesItem) {
        if (this.topologies == null) {
            this.topologies = new ArrayList<CloudsStormSubTopology>();
        }
        this.topologies.add(topologiesItem);
        return this;
    }

    /**
     * Get topologies
     *
     * @return topologies
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudsStormSubTopology> getTopologies() {
        return topologies;
    }

    public void setTopologies(List<CloudsStormSubTopology> topologies) {
        this.topologies = topologies;
    }

    public CloudsStormTopTopology subnets(List<CloudsStormSubnets> subnets) {
        this.subnets = subnets;
        return this;
    }

    public CloudsStormTopTopology addSubnetsItem(CloudsStormSubnets subnetsItem) {
        if (this.subnets == null) {
            this.subnets = new ArrayList<CloudsStormSubnets>();
        }
        this.subnets.add(subnetsItem);
        return this;
    }

    /**
     * Get subnets
     *
     * @return subnets
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudsStormSubnets> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<CloudsStormSubnets> subnets) {
        this.subnets = subnets;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormTopTopology cloudsStormTopTopology = (CloudsStormTopTopology) o;
        return Objects.equals(this.userName, cloudsStormTopTopology.userName)
                && Objects.equals(this.publicKeyPath, cloudsStormTopTopology.publicKeyPath)
                && Objects.equals(this.topologies, cloudsStormTopTopology.topologies)
                && Objects.equals(this.subnets, cloudsStormTopTopology.subnets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, publicKeyPath, topologies, subnets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormTopTopology {\n");

        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    publicKeyPath: ").append(toIndentedString(publicKeyPath)).append("\n");
        sb.append("    topologies: ").append(toIndentedString(topologies)).append("\n");
        sb.append("    subnets: ").append(toIndentedString(subnets)).append("\n");
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
