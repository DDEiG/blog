package monkeyforest.blog.domain.user.persistence.entity.field;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailAttributeConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.getUsername();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }
}
