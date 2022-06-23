package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CloudsStormSubMembers
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-11T15:13:55.016Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CloudsStormSubMembers {
    @JsonProperty("vmName")
    private String vmName = null;

    @JsonProperty("address")
    private String address = null;

    public CloudsStormSubMembers vmName(String vmName) {
        this.vmName = vmName;
        return this;
    }

    /**
     * Get vmName
     *
     * @return vmName
     **/
    @ApiModelProperty(value = "")


    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public CloudsStormSubMembers address(String address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     *
     * @return address
     **/
    @ApiModelProperty(value = "")


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudsStormSubMembers cloudsStormSubMembers = (CloudsStormSubMembers) o;
        return Objects.equals(this.vmName, cloudsStormSubMembers.vmName) &&
                Objects.equals(this.address, cloudsStormSubMembers.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vmName, address);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudsStormSubMembers {\n");

        sb.append("    vmName: ").append(toIndentedString(vmName)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
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

