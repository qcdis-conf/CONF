package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * InfrasCode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-31T12:57:31.148Z")

public class InfrasCode {

    @JsonProperty("CodeType")
    private CodeTypeEnum codeType = null;
    @JsonProperty("OpCode")
    private OpCode opCode = null;
    @JsonProperty("Count")
    private Integer count = null;

    public InfrasCode codeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
        return this;
    }

//    @JsonProperty("OpCodes")
//    @Valid
//    private List<OpCode> opCodes = null;

    /**
     * Get codeType
     *
     * @return codeType
     */
    @ApiModelProperty(value = "")

    public CodeTypeEnum getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    public InfrasCode opCode(OpCode opCode) {
        this.opCode = opCode;
        return this;
    }

    /**
     * Get opCode
     *
     * @return opCode
     */
    @ApiModelProperty(value = "")

    @Valid

    public OpCode getOpCode() {
        return opCode;
    }

    public void setOpCode(OpCode opCode) {
        this.opCode = opCode;
    }

    public InfrasCode count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Get count
     *
     * @return count
     */
    @ApiModelProperty(value = "")

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InfrasCode infrasCode = (InfrasCode) o;
        return Objects.equals(this.codeType, infrasCode.codeType)
                && Objects.equals(this.opCode, infrasCode.opCode)
                && Objects.equals(this.count, infrasCode.count);
//                && Objects.equals(this.opCodes, infrasCode.opCodes);
    }

//    public InfrasCode opCodes(List<OpCode> opCodes) {
//        this.opCodes = opCodes;
//        return this;
//    }
//
//    public InfrasCode addOpCodesItem(OpCode opCodesItem) {
//        if (this.opCodes == null) {
//            this.opCodes = new ArrayList<OpCode>();
//        }
//        this.opCodes.add(opCodesItem);
//        return this;
//    }
//
//    /**
//     * Get opCodes
//     *
//     * @return opCodes
//  *
//     */
//    @ApiModelProperty(value = "")
//
//    @Valid
//
//    public List<OpCode> getOpCodes() {
//        return opCodes;
//    }
//
//    public void setOpCodes(List<OpCode> opCodes) {
//        this.opCodes = opCodes;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(codeType, opCode, count); //, opCodes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InfrasCode {\n");

        sb.append("    codeType: ").append(toIndentedString(codeType)).append("\n");
        sb.append("    opCode: ").append(toIndentedString(opCode)).append("\n");
        sb.append("    count: ").append(toIndentedString(count)).append("\n");
//        sb.append("    opCodes: ").append(toIndentedString(opCodes)).append("\n");
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

    /**
     * Gets or Sets codeType
     */
    public enum CodeTypeEnum {
        SEQ("SEQ"),
        LOOP("LOOP");

        private String value;

        CodeTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static CodeTypeEnum fromValue(String text) {
            for (CodeTypeEnum b : CodeTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
