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
 * DB
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-11T15:52:11.688Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DB {

    @JsonProperty("cloudDBs")
    @Valid
    private List<CloudDB> cloudDBs = null;

    public DB cloudDBs(List<CloudDB> cloudDBs) {
        this.cloudDBs = cloudDBs;
        return this;
    }

    public DB addCloudDBsItem(CloudDB cloudDBsItem) {
        if (this.cloudDBs == null) {
            this.cloudDBs = new ArrayList<>();
        }
        this.cloudDBs.add(cloudDBsItem);
        return this;
    }

    /**
     * Get cloudDBs
     *
     * @return cloudDBs
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudDB> getCloudDBs() {
        return cloudDBs;
    }

    public void setCloudDBs(List<CloudDB> cloudDBs) {
        this.cloudDBs = cloudDBs;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DB DB = (DB) o;
        return Objects.equals(this.cloudDBs, DB.cloudDBs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cloudDBs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DB {\n");

        sb.append("    cloudDBs: ").append(toIndentedString(cloudDBs)).append("\n");
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
