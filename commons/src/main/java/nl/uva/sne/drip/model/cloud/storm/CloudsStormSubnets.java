package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CloudsStormSubnets
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-11T15:13:55.016Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CloudsStormSubnets {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("subnet")
    private String subnet = null;

    @JsonProperty("netmask")
    private String netmask = null;

    @JsonProperty("members")
    @Valid
    private List<CloudsStormSubMembers> members = null;

    public CloudsStormSubnets name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(value = "")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloudsStormSubnets subnet(String subnet) {
        this.subnet = subnet;
        return this;
    }

    /**
     * Get subnet
     *
     * @return subnet
     **/
    @ApiModelProperty(value = "")


    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public CloudsStormSubnets netmask(String netmask) {
        this.netmask = netmask;
        return this;
    }

    /**
     * Get netmask
     *
     * @return netmask
     **/
    @ApiModelProperty(value = "")


    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public CloudsStormSubnets members(List<CloudsStormSubMembers> members) {
        this.members = members;
        return this;
    }

    public CloudsStormSubnets addMembersItem(CloudsStormSubMembers membersItem) {
        if (this.members == null) {
            this.members = new ArrayList<CloudsStormSubMembers>();
        }
        this.members.add(membersItem);
        return this;
    }

    /**
     * Get members
     *
     * @return members
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudsStormSubMembers> getMembers() {
        return members;
    }

    public void setMembers(List<CloudsStormSubMembers> members) {
        this.members = members;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormSubnets cloudsStormSubnets = (CloudsStormSubnets) o;
        return Objects.equals(this.name, cloudsStormSubnets.name) &&
                Objects.equals(this.subnet, cloudsStormSubnets.subnet) &&
                Objects.equals(this.netmask, cloudsStormSubnets.netmask) &&
                Objects.equals(this.members, cloudsStormSubnets.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subnet, netmask, members);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormSubnets {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    subnet: ").append(toIndentedString(subnet)).append("\n");
        sb.append("    netmask: ").append(toIndentedString(netmask)).append("\n");
        sb.append("    members: ").append(toIndentedString(members)).append("\n");
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

