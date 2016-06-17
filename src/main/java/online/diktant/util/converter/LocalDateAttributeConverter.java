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

package online.diktant.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.sql.Date;

/**
 * Convert a {@link LocalDate} into database column representation as {@link Date} and back again.
 *
 * @author Petrov Egor. Created 14.06.16.
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    /**
     * Convert a {@link LocalDate} into {@link Date}.
     *
     * @param localDate value of LocalDate.
     * @return Date value.
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    /**
     * Convert a {@link Date} into {@link LocalDate}.
     *
     * @param sqlDate value of Date.
     * @return LocalDate value.
     */
    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
