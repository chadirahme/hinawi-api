package com.hinawi.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = false)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toInstant()
        .atZone(ZoneId.systemDefault())).toLocalDateTime();
    }
}