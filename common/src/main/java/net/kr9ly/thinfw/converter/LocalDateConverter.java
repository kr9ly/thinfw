package net.kr9ly.thinfw.converter;

import org.jooq.Converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Copyright 2015 kr9ly
 * <br />
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <br />
 * http://www.apache.org/licenses/LICENSE-2.0
 * <br />
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class LocalDateConverter implements Converter<Date, LocalDate> {

    @Override
    public LocalDate from(Date date) {
        return date.toLocalDate();
    }

    @Override
    public Date to(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    @Override
    public Class<Date> fromType() {
        return Date.class;
    }

    @Override
    public Class<LocalDate> toType() {
        return LocalDate.class;
    }
}
