/*
 * Copyright 2017 S. Koulouzis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.uva.sne.drip.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

/**
 * @author S. Koulouzis.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageParameter implements Serializable {

    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String VALUE = "value";
    public static final String ENCODING = "encoding";
    public static final String TOSCA_TEMPLATE = "tosca_template";
    private String url;
    private String encoding;
    private String value;
    private String name;
    private Map<String, String> attributes;

    public String getURL() {
        return this.url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}
