package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CredentialInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-31T12:57:31.148Z")

public class CredentialInfo {

    @JsonProperty("userKeyName")
    private String userKeyName = null;

    @JsonProperty("keyAlias")
    private String keyAlias = null;

    @JsonProperty("keyPassword")
    private String keyPassword = null;

    @JsonProperty("proxyFileName")
    private String proxyFileName = null;

    @JsonProperty("trustedCertDirName")
    private String trustedCertDirName = null;

    @JsonProperty("accessKey")
    private String accessKey = null;

    @JsonProperty("secretKey")
    private String secretKey = null;

    public CredentialInfo userKeyName(String userKeyName) {
        this.userKeyName = userKeyName;
        return this;
    }

    /**
     * Get userKeyName
     *
     * @return userKeyName
     */
    @ApiModelProperty(value = "")

    public String getUserKeyName() {
        return userKeyName;
    }

    public void setUserKeyName(String userKeyName) {
        this.userKeyName = userKeyName;
    }

    public CredentialInfo keyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
        return this;
    }

    /**
     * Get keyAlias
     *
     * @return keyAlias
     */
    @ApiModelProperty(value = "")

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public CredentialInfo keyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
        return this;
    }

    /**
     * Get keyPassword
     *
     * @return keyPassword
     */
    @ApiModelProperty(value = "")

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public CredentialInfo proxyFileName(String proxyFileName) {
        this.proxyFileName = proxyFileName;
        return this;
    }

    /**
     * Get proxyFileName
     *
     * @return proxyFileName
     */
    @ApiModelProperty(value = "")

    public String getProxyFileName() {
        return proxyFileName;
    }

    public void setProxyFileName(String proxyFileName) {
        this.proxyFileName = proxyFileName;
    }

    public CredentialInfo trustedCertDirName(String trustedCertDirName) {
        this.trustedCertDirName = trustedCertDirName;
        return this;
    }

    /**
     * Get trustedCertDirName
     *
     * @return trustedCertDirName
     */
    @ApiModelProperty(value = "")

    public String getTrustedCertDirName() {
        return trustedCertDirName;
    }

    public void setTrustedCertDirName(String trustedCertDirName) {
        this.trustedCertDirName = trustedCertDirName;
    }

    public CredentialInfo accessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    /**
     * Get accessKey
     *
     * @return accessKey
     */
    @ApiModelProperty(value = "")

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public CredentialInfo secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * Get secretKey
     *
     * @return secretKey
     */
    @ApiModelProperty(value = "")

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CredentialInfo credentialInfo = (CredentialInfo) o;
        return Objects.equals(this.userKeyName, credentialInfo.userKeyName)
                && Objects.equals(this.keyAlias, credentialInfo.keyAlias)
                && Objects.equals(this.keyPassword, credentialInfo.keyPassword)
                && Objects.equals(this.proxyFileName, credentialInfo.proxyFileName)
                && Objects.equals(this.trustedCertDirName, credentialInfo.trustedCertDirName)
                && Objects.equals(this.accessKey, credentialInfo.accessKey)
                && Objects.equals(this.secretKey, credentialInfo.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userKeyName, keyAlias, keyPassword, proxyFileName, trustedCertDirName, accessKey, secretKey);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CredentialInfo {\n");

        sb.append("    userKeyName: ").append(toIndentedString(userKeyName)).append("\n");
        sb.append("    keyAlias: ").append(toIndentedString(keyAlias)).append("\n");
        sb.append("    keyPassword: ").append(toIndentedString(keyPassword)).append("\n");
        sb.append("    proxyFileName: ").append(toIndentedString(proxyFileName)).append("\n");
        sb.append("    trustedCertDirName: ").append(toIndentedString(trustedCertDirName)).append("\n");
        sb.append("    accessKey: ").append(toIndentedString(accessKey)).append("\n");
        sb.append("    secretKey: ").append(toIndentedString(secretKey)).append("\n");
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
