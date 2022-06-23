package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DBInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-12T18:26:54.530Z")

public class DBInfo {

    @JsonProperty("GlobalEntry")
    private String globalEntry = null;

    @JsonProperty("DCMetaInfo")
    @Valid
    private List<CloudStormDCMetaInfo> dcMetaInfo = null;

    public DBInfo globalEntry(String globalEntry) {
        this.globalEntry = globalEntry;
        return this;
    }

    /**
     * Get globalEntry
     *
     * @return globalEntry
     */
    @ApiModelProperty(value = "")

    public String getGlobalEntry() {
        return globalEntry;
    }

    public void setGlobalEntry(String globalEntry) {
        this.globalEntry = globalEntry;
    }

    public DBInfo dcMetaInfo(List<CloudStormDCMetaInfo> dcMetaInfo) {
        this.dcMetaInfo = dcMetaInfo;
        return this;
    }

    public DBInfo addDcMetaInfoItem(CloudStormDCMetaInfo dcMetaInfoItem) {
        if (this.dcMetaInfo == null) {
            this.dcMetaInfo = new ArrayList<CloudStormDCMetaInfo>();
        }
        this.dcMetaInfo.add(dcMetaInfoItem);
        return this;
    }

    /**
     * Get dcMetaInfo
     *
     * @return dcMetaInfo
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CloudStormDCMetaInfo> getDcMetaInfo() {
        return dcMetaInfo;
    }

    public void setDcMetaInfo(List<CloudStormDCMetaInfo> dcMetaInfo) {
        this.dcMetaInfo = dcMetaInfo;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DBInfo dbInfo = (DBInfo) o;
        return Objects.equals(this.globalEntry, dbInfo.globalEntry)
                && Objects.equals(this.dcMetaInfo, dbInfo.dcMetaInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalEntry, dcMetaInfo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DBInfo {\n");

        sb.append("    globalEntry: ").append(toIndentedString(globalEntry)).append("\n");
        sb.append("    dcMetaInfo: ").append(toIndentedString(dcMetaInfo)).append("\n");
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
