package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CloudCredentialDB
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-26T13:19:20.152Z")

public class CloudCredentialDB {

    @JsonProperty("cloudCreds")
    @Valid
    private List<CloudCred> cloudCreds = null;

    public CloudCredentialDB cloudCreds(List<CloudCred> cloudCreds) {
        this.cloudCreds = cloudCreds;
        return this;
    }

    public CloudCredentialDB addCloudCredsItem(CloudCred cloudCredsItem) {
        if (this.cloudCreds == null) {
            this.cloudCreds = new ArrayList<CloudCred>();
        }
        this.cloudCreds.add(cloudCredsItem);
        return this;
    }

    /**
     * Get cloudCreds
     *
     * @return cloudCreds
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudCred> getCloudCreds() {
        return cloudCreds;
    }

    public void setCloudCreds(List<CloudCred> cloudCreds) {
        this.cloudCreds = cloudCreds;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CloudCredentialDB cloudCredentialDB = (CloudCredentialDB) o;
        return Objects.equals(this.cloudCreds, cloudCredentialDB.cloudCreds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cloudCreds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CloudCredentialDB {\n");

        sb.append("    cloudCreds: ").append(toIndentedString(cloudCreds)).append("\n");
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
