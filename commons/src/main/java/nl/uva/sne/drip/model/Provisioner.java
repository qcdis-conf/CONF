package nl.uva.sne.drip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Provisioner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-26T15:03:16.208Z")

public class Provisioner {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("version")
    private String version = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("tosca_interface_type")
    private String toscaInterfaceType = null;

    public Provisioner name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @ApiModelProperty(value = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Provisioner version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get version
     *
     * @return version
     */
    @ApiModelProperty(value = "")

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Provisioner description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     */
    @ApiModelProperty(value = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Provisioner toscaInterfaceType(String toscaInterfaceType) {
        this.toscaInterfaceType = toscaInterfaceType;
        return this;
    }

    /**
     * Get toscaInterfaceType
     *
     * @return toscaInterfaceType
     */
    @ApiModelProperty(value = "")

    public String getToscaInterfaceType() {
        return toscaInterfaceType;
    }

    public void setToscaInterfaceType(String toscaInterfaceType) {
        this.toscaInterfaceType = toscaInterfaceType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Provisioner provisioner = (Provisioner) o;
        return Objects.equals(this.name, provisioner.name)
                && Objects.equals(this.version, provisioner.version)
                && Objects.equals(this.description, provisioner.description)
                && Objects.equals(this.toscaInterfaceType, provisioner.toscaInterfaceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version, description, toscaInterfaceType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Provisioner {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    toscaInterfaceType: ").append(toIndentedString(toscaInterfaceType)).append("\n");
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
