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

import online.diktant.domain.BaseEntity;

import javax.persistence.*;

/**
 * Simple JavaBean domain object representing a sentence from dictation.
 *
 * @author Petrov Egor. Created 14.06.16.
 */
@Entity
@Table(name = "sentence")
public class Sentence extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "speech", nullable = false)
    private byte[] speech;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dictation_id", nullable = false)
    private Dictation dictation;

    public Sentence() {
    }

    public Sentence(Integer number, String text, byte[] speech) {
        this.number = number;
        this.text = text;
        this.speech = speech.clone();
    }

    /**
     * Sets new speech.
     *
     * @param speech New value of speech.
     */
    protected void setSpeech(byte[] speech) {
        this.speech = speech;
    }

    /**
     * Sets new text.
     *
     * @param text New value of text.
     */
    protected void setText(String text) {
        this.text = text;
    }

    /**
     * Gets speech.
     *
     * @return Value of speech.
     */
    public byte[] getSpeech() {
        return speech.clone();
    }

    /**
     * Gets text.
     *
     * @return Value of text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets new number.
     *
     * @param number New value of number.
     */
    protected void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets number.
     *
     * @return Value of number.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Gets dictation.
     *
     * @return Value of dictation.
     */
    protected Dictation getDictation() {
        return dictation;
    }

    /**
     * Sets dictation once for new instance.
     *
     * @param dictation New value of dictation.
     */
    void setDictation(Dictation dictation) {
        if (dictation == null)
            this.dictation = dictation;
    }
}
