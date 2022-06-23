package nl.uva.sne.drip.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

/**
 * NodeTemplate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T16:27:40.329Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeTemplate {

    @JsonProperty("derived_from")
    private String derivedFrom = null;

    @JsonProperty("properties")
    @Valid
    private Map<String, Object> properties = null;

    @JsonProperty("requirements")
    @Valid
    private List<Map<String, Object>> requirements = null;

    @JsonProperty("interfaces")
    @Valid
    private Map<String, Object> interfaces = null;

    @JsonProperty("capabilities")
    @Valid
    private Map<String, Object> capabilities = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("directives")
    @Valid
    private List<String> directives = null;

    @JsonProperty("attributes")
    @Valid
    private Map<String, Object> attributes = null;

    @JsonProperty("artifacts")
    @Valid
    private Map<String, Object> artifacts = null;

    public NodeTemplate derivedFrom(String derivedFrom) {
        this.derivedFrom = derivedFrom;
        return this;
    }

    /**
     * Get derivedFrom
     *
     * @return derivedFrom
     */
    @ApiModelProperty(value = "")

    public String getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(String derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    public NodeTemplate properties(Map<String, Object> properties) {
        this.properties = properties;
        return this;
    }

    public NodeTemplate putPropertiesItem(String key, Object propertiesItem) {
        if (this.properties == null) {
            this.properties = new HashMap<String, Object>();
        }
        this.properties.put(key, propertiesItem);
        return this;
    }

    /**
     * Get properties
     *
     * @return properties
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public NodeTemplate requirements(List<Map<String, Object>> requirements) {
        this.requirements = requirements;
        return this;
    }

    public NodeTemplate addRequirementsItem(Map<String, Object> requirementsItem) {
        if (this.requirements == null) {
            this.requirements = new ArrayList<Map<String, Object>>();
        }
        this.requirements.add(requirementsItem);
        return this;
    }

    /**
     * Get requirements
     *
     * @return requirements
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Map<String, Object>> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Map<String, Object>> requirements) {
        this.requirements = requirements;
    }

    public NodeTemplate interfaces(Map<String, Object> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public NodeTemplate putInterfacesItem(String key, Object interfacesItem) {
        if (this.interfaces == null) {
            this.interfaces = new HashMap<String, Object>();
        }
        this.interfaces.put(key, interfacesItem);
        return this;
    }

    /**
     * Get interfaces
     *
     * @return interfaces
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Map<String, Object> interfaces) {
        this.interfaces = interfaces;
    }

    public NodeTemplate capabilities(Map<String, Object> capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    public NodeTemplate putCapabilitiesItem(String key, Object capabilitiesItem) {
        if (this.capabilities == null) {
            this.capabilities = new HashMap<String, Object>();
        }
        this.capabilities.put(key, capabilitiesItem);
        return this;
    }

    /**
     * Get capabilities
     *
     * @return capabilities
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Map<String, Object> capabilities) {
        this.capabilities = capabilities;
    }

    public NodeTemplate type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @ApiModelProperty(value = "")

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NodeTemplate description(String description) {
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

    public NodeTemplate directives(List<String> directives) {
        this.directives = directives;
        return this;
    }

    public NodeTemplate addDirectivesItem(String directivesItem) {
        if (this.directives == null) {
            this.directives = new ArrayList<String>();
        }
        this.directives.add(directivesItem);
        return this;
    }

    /**
     * Get directives
     *
     * @return directives
     */
    @ApiModelProperty(value = "")

    public List<String> getDirectives() {
        return directives;
    }

    public void setDirectives(List<String> directives) {
        this.directives = directives;
    }

    public NodeTemplate attributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public NodeTemplate putAttributesItem(String key, Object attributesItem) {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, Object>();
        }
        this.attributes.put(key, attributesItem);
        return this;
    }

    /**
     * Get attributes
     *
     * @return attributes
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public NodeTemplate artifacts(Map<String, Object> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    public NodeTemplate putArtifactsItem(String key, Object artifactsItem) {
        if (this.artifacts == null) {
            this.artifacts = new HashMap<String, Object>();
        }
        this.artifacts.put(key, artifactsItem);
        return this;
    }

    /**
     * Get artifacts
     *
     * @return artifacts
     */
    @ApiModelProperty(value = "")

    public Map<String, Object> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Map<String, Object> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeTemplate nodeTemplate = (NodeTemplate) o;
        return Objects.equals(this.derivedFrom, nodeTemplate.derivedFrom)
                && Objects.equals(this.properties, nodeTemplate.properties)
                && Objects.equals(this.requirements, nodeTemplate.requirements)
                && Objects.equals(this.interfaces, nodeTemplate.interfaces)
                && Objects.equals(this.capabilities, nodeTemplate.capabilities)
                && Objects.equals(this.type, nodeTemplate.type)
                && Objects.equals(this.description, nodeTemplate.description)
                && Objects.equals(this.directives, nodeTemplate.directives)
                && Objects.equals(this.attributes, nodeTemplate.attributes)
                && Objects.equals(this.artifacts, nodeTemplate.artifacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(derivedFrom, properties, requirements, interfaces, capabilities, type, description, directives, attributes, artifacts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NodeTemplate {\n");

        sb.append("    derivedFrom: ").append(toIndentedString(derivedFrom)).append("\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
        sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
        sb.append("    interfaces: ").append(toIndentedString(interfaces)).append("\n");
        sb.append("    capabilities: ").append(toIndentedString(capabilities)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    directives: ").append(toIndentedString(directives)).append("\n");
        sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
        sb.append("    artifacts: ").append(toIndentedString(artifacts)).append("\n");
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
