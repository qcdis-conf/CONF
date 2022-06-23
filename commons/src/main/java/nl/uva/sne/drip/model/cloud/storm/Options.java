package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Options
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-31T12:57:31.148Z")

public class Options {

    @JsonProperty("Src")
    private String src = null;

    @JsonProperty("Dst")
    private String dst = null;

    public Options src(String src) {
        this.src = src;
        return this;
    }

    /**
     * Get src
     *
     * @return src
     */
    @ApiModelProperty(value = "")

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Options dst(String dst) {
        this.dst = dst;
        return this;
    }

    /**
     * Get dst
     *
     * @return dst
     */
    @ApiModelProperty(value = "")

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Options options = (Options) o;
        return Objects.equals(this.src, options.src)
                && Objects.equals(this.dst, options.dst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Options {\n");

        sb.append("    src: ").append(toIndentedString(src)).append("\n");
        sb.append("    dst: ").append(toIndentedString(dst)).append("\n");
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
