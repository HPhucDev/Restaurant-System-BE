package vn.com.foodsystem.dao.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vn.com.foodsystem.dao.enums.Roles;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Roles, String> {
	@Override
	public String convertToDatabaseColumn(Roles roles) {
		return (roles == null) ? null : roles.getValue();
	}

	@Override
	public Roles convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}

		return Stream.of(Roles.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
