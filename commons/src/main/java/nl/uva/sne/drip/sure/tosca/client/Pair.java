/*
 * tosca-sure
 * TOSCA Simple qUeRy sErvice (SURE).
 *
 * OpenAPI spec version: 1.0.0
 * Contact: S.Koulouzis@uva.nl
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package nl.uva.sne.drip.sure.tosca.client;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-12-28T19:11:27.492Z")
public class Pair {
    private String name = "";
    private String value = "";

    public Pair(String name, String value) {
        setName(name);
        setValue(value);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (!isValidString(name)) return;

        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    private void setValue(String value) {
        if (!isValidString(value)) return;

        this.value = value;
    }

    private boolean isValidString(String arg) {
        if (arg == null) return false;
        if (arg.trim().isEmpty()) return false;

        return true;
    }
}
