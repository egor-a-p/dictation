/*
 * Copyright 2016 the original author.
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

package online.diktant.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object adds a name property to {@link BaseEntity}.
 * Used as a base class for objects needing these properties.
 *
 * @author Petrov Egor. Created 14.06.16.
 */
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    protected String name;

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    protected void setName(String name) {
        this.name = name;
    }
}
