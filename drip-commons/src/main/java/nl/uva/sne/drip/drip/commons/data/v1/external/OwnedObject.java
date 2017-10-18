/*
 * Copyright 2017 S. Koulouzis, Wang Junchao, Huan Zhou, Yang Hu 
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
package nl.uva.sne.drip.drip.commons.data.v1.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.webcohesion.enunciate.metadata.DocumentationExample;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

/**
 * This is the base class for users to own resources. Many classes extend this
 * class
 *
 * @author S. Koulouzis
 */
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnedObject {

    @Id
    private String id;

    private Long timestamp = System.currentTimeMillis();

    @NotNull
    private String owner;

    /**
     * The owner (username) for the particular object. This value is set when
     * the DAO saves the object based on the principal how made the call
     *
     * @return the owner
     */
    @DocumentationExample("user1")
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the ownerID to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * The UID of this object. This value is auto generated by the DAO when the
     * object is saved.
     *
     * @return the id
     */
    @DocumentationExample("58e3946e0fb4f562d84ba1ad")
    public String getId() {
        return id;
    }

    /**
     * The object's creation date in unix time stamp
     * @return the timestamp
     */
    @DocumentationExample("1499793079011")
    public Long getTimestamp() {
        return timestamp;
    }

}
