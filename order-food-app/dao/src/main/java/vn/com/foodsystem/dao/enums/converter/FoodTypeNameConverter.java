package vn.com.foodsystem.dao.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vn.com.foodsystem.dao.enums.FoodTypeName;

@Converter(autoApply = true)
public class FoodTypeNameConverter implements AttributeConverter<FoodTypeName, String> {
	@Override
	public String convertToDatabaseColumn(FoodTypeName foodTypeName) {
		return (foodTypeName == null) ? null : foodTypeName.getValue();
	}

	@Override
	public FoodTypeName convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}

		return Stream.of(FoodTypeName.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
