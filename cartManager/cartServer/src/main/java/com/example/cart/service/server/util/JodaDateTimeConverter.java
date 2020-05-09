package com.example.cart.service.server.util;

import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

/**
 * Created by palash.v on 08/12/15.
 */
@Converter(autoApply = true)
@NoArgsConstructor
public class JodaDateTimeConverter implements AttributeConverter<DateTime, Timestamp> {

    public Timestamp convertToDatabaseColumn(DateTime attribute) {
        return attribute == null ? null : new Timestamp(attribute.getMillis());
    }

    public DateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : new DateTime(dbData.getTime());
    }
}
