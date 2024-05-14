package monkeyforest.blog.domain.user.persistence.entity.field;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberAttributeConverter implements AttributeConverter<PhoneNumber, String> {
    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        return attribute.getPhoneNumber();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        return new PhoneNumber(dbData);
    }
}
