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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 *
 *
 * @author S. Koulouzis.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {

    @NotNull
    private String owner;
    @NotNull
    private Long creationDate;

    private List<MessageParameter> parameters;

    private ToscaTemplate toscaTemplate;

    public Long getCreationDate() {
        return this.creationDate;
    }

    public void setParameters(List<MessageParameter> parameters) {
        this.parameters = parameters;
    }

    public List<MessageParameter> getParameters() {
        return this.parameters;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the toscaTemplate
     */
    public ToscaTemplate getToscaTemplate() {
        return toscaTemplate;
    }

    /**
     * @param toscaTemplate the toscaTemplate to set
     */
    public void setToscaTemplate(ToscaTemplate toscaTemplate) {
        this.toscaTemplate = toscaTemplate;
    }

}
