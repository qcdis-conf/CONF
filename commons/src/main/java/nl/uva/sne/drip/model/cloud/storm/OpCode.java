package nl.uva.sne.drip.model.cloud.storm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * OpCode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-31T12:57:31.148Z")

public class OpCode {

    @JsonProperty("Operation")
    private OperationEnum operation = null;
    @JsonProperty("ObjectType")
    private ObjectTypeEnum objectType = null;
    @JsonProperty("Objects")
    private String objects = null;
    @JsonProperty("Command")
    private String command = null;
    @JsonProperty("Log")
    private Boolean log = null;
    @JsonProperty("Options")
    private Options options = null;

    public OpCode operation(OperationEnum operation) {
        this.operation = operation;
        return this;
    }

    /**
     * Get operation
     *
     * @return operation
     */
    @ApiModelProperty(value = "")

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public OpCode objectType(ObjectTypeEnum objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * Get objectType
     *
     * @return objectType
     */
    @ApiModelProperty(value = "")

    public ObjectTypeEnum getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectTypeEnum objectType) {
        this.objectType = objectType;
    }

    public OpCode objects(String objects) {
        this.objects = objects;
        return this;
    }

    /**
     * Get objects
     *
     * @return objects
     */
    @ApiModelProperty(value = "")

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public OpCode command(String command) {
        this.command = command;
        return this;
    }

    /**
     * Get command
     *
     * @return command
     */
    @ApiModelProperty(value = "")

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public OpCode log(Boolean log) {
        this.log = log;
        return this;
    }

    /**
     * Get log
     *
     * @return log
     */
    @ApiModelProperty(value = "")

    public Boolean isLog() {
        return log;
    }

    public void setLog(Boolean log) {
        this.log = log;
    }

    public OpCode options(Options options) {
        this.options = options;
        return this;
    }

    /**
     * Get options
     *
     * @return options
     */
    @ApiModelProperty(value = "")

    @Valid

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpCode opCode = (OpCode) o;
        return Objects.equals(this.operation, opCode.operation)
                && Objects.equals(this.objectType, opCode.objectType)
                && Objects.equals(this.objects, opCode.objects)
                && Objects.equals(this.command, opCode.command)
                && Objects.equals(this.log, opCode.log)
                && Objects.equals(this.options, opCode.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, objectType, objects, command, log, options);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OpCode {\n");

        sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
        sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
        sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
        sb.append("    command: ").append(toIndentedString(command)).append("\n");
        sb.append("    log: ").append(toIndentedString(log)).append("\n");
        sb.append("    options: ").append(toIndentedString(options)).append("\n");
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
     * Gets or Sets operation
     */
    public enum OperationEnum {
        PROVISION("provision"),
        DELETE("delete"),
        EXECUTE("execute"),
        PUT("put"),
        GET("get"),
        VSCALE("vscale"),
        HSCALE("hscale"),
        RECOVER("recover"),
        START("start"),
        STOP("stop");

        private String value;

        OperationEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static OperationEnum fromValue(String text) {
            for (OperationEnum b : OperationEnum.values()) {
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

    /**
     * Gets or Sets objectType
     */
    public enum ObjectTypeEnum {
        SUBTOPOLOGY("SubTopology"),
        VM("VM"),
        REQ("REQ");

        private String value;

        ObjectTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ObjectTypeEnum fromValue(String text) {
            for (ObjectTypeEnum b : ObjectTypeEnum.values()) {
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
