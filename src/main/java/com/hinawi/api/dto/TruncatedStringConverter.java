package com.hinawi.api.dto;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class TruncatedStringConverter implements AttributeConverter<String, String> {
    private static final int LIMIT = 1;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        } else if (attribute.length() > LIMIT) {
            return attribute.substring(0, LIMIT);
        } else {
            return attribute;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}