package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Credential
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-31T12:57:31.148Z")

public class Credential {

    @JsonProperty("protocol")
    private String protocol = null;

    @JsonProperty("token_type")
    private String tokenType = null;

    @JsonProperty("token")
    private String token = null;

    @JsonProperty("keys")
    @Valid
    private Map<String, String> keys = null;

    @JsonProperty("user")
    private String user = null;

    @JsonProperty("cloud_provider_name")
    private String cloudProviderName = null;

    public Credential protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * The optional protocol name. e.g. http,xauth,oauth2,ssh
     *
     * @return protocol
     */
    @ApiModelProperty(value = "The optional protocol name. e.g. http,xauth,oauth2,ssh")

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Credential tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    /**
     * The required token type. default: password. e.g. basic_auth,X-Auth-Token,
     * bearer, identifier
     *
     * @return tokenType
     */
    @ApiModelProperty(value = "The required token type. default: password. e.g. basic_auth,X-Auth-Token, bearer, identifier")

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Credential token(String token) {
        this.token = token;
        return this;
    }

    /**
     * The required token used as a credential for authorization or access to a
     * networked resource. e.g. mypassword, myusername:mypassword,
     * 604bbe45ac7143a79e14f3158df67091, keypair_id
     *
     * @return token
     */
    @ApiModelProperty(value = "The required token used as a credential for authorization or access to a networked resource. e.g. mypassword, myusername:mypassword,  604bbe45ac7143a79e14f3158df67091, keypair_id")

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Credential keys(Map<String, String> keys) {
        this.keys = keys;
        return this;
    }

    public Credential putKeysItem(String key, String keysItem) {
        if (this.keys == null) {
            this.keys = new HashMap<String, String>();
        }
        this.keys.put(key, keysItem);
        return this;
    }

    /**
     * The optional list of protocol-specific keys or assertions.
     *
     * @return keys
     */
    @ApiModelProperty(value = "The optional list of protocol-specific keys or assertions.")

    public Map<String, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }

    public Credential user(String user) {
        this.user = user;
        return this;
    }

    /**
     * The optional user (name or ID) used for non-token based credentials.
     *
     * @return user
     */
    @ApiModelProperty(value = "The optional user (name or ID) used for non-token based credentials.")

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Credential cloudProviderName(String cloudProviderName) {
        this.cloudProviderName = cloudProviderName;
        return this;
    }

    /**
     * The cloud provider name e.g. ec2.
     *
     * @return cloudProviderName
     */
    @ApiModelProperty(value = "The cloud provider name e.g. ec2.")

    public String getCloudProviderName() {
        return cloudProviderName;
    }

    public void setCloudProviderName(String cloudProviderName) {
        this.cloudProviderName = cloudProviderName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Credential credential = (Credential) o;
        return Objects.equals(this.protocol, credential.protocol)
                && Objects.equals(this.tokenType, credential.tokenType)
                && Objects.equals(this.token, credential.token)
                && Objects.equals(this.keys, credential.keys)
                && Objects.equals(this.user, credential.user)
                && Objects.equals(this.cloudProviderName, credential.cloudProviderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, tokenType, token, keys, user, cloudProviderName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Credential {\n");

        sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
        sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    keys: ").append(toIndentedString(keys)).append("\n");
        sb.append("    user: ").append(toIndentedString(user)).append("\n");
        sb.append("    cloudProviderName: ").append(toIndentedString(cloudProviderName)).append("\n");
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
