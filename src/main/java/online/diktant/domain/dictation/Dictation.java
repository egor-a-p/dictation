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

package online.diktant.domain.dictation;

import online.diktant.domain.NamedEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple JavaBean domain object representing an information about dictation.
 *
 * @author Petrov Egor. Created 14.06.16.
 */
@Entity
@Table(name = "dictation")
public class Dictation extends NamedEntity {

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "level")
    private Integer level;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dictation")
    @OrderBy("number ASC")
    private List<Sentence> sentences;

    public Dictation() {
    }

    public Dictation(String name, String author, Integer length, Integer level, List<Sentence> sentences) {
        this.name = name;
        this.author = author;
        this.length = length;
        this.level = level;
        this.sentences = new ArrayList<>(sentences);
        sentences.forEach(s -> s.setDictation(this));
    }

    /**
     * Sets new createdDate.
     *
     * @param createdDate New value of createdDate.
     */
    protected void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets createdDate.
     *
     * @return Value of createdDate.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets new length.
     *
     * @param length New value of length.
     */
    protected void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Gets level.
     *
     * @return Value of level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Gets author.
     *
     * @return Value of author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets new author.
     *
     * @param author New value of author.
     */
    protected void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets new level.
     *
     * @param level New value of level.
     */
    protected void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Gets length.
     *
     * @return Value of length.
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Gets sentences.
     *
     * @return Value of sentences.
     */
    public List<Sentence> getSentences() {
        return Collections.unmodifiableList(sentences);
    }

    /**
     * Sets new sentences.
     *
     * @param sentences New value of sentences.
     */
    protected void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}
