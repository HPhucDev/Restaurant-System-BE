package vn.com.foodsystem.dao.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vn.com.foodsystem.dao.enums.FoodItemStatus;
import vn.com.foodsystem.dao.enums.Gender;

@Converter(autoApply = true)
public class FoodItemStatusConverter implements AttributeConverter<FoodItemStatus, String> {
	@Override
	public String convertToDatabaseColumn(FoodItemStatus foodItemStatus) {
		return (foodItemStatus == null) ? null : foodItemStatus.getValue();
	}

	@Override
	public FoodItemStatus convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}

		return Stream.of(FoodItemStatus.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
