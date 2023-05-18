package vn.com.foodsystem.dao.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vn.com.foodsystem.dao.enums.OrderStatus;

@Converter(autoApply = true)
public class OrderStatusConvert implements AttributeConverter<OrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(OrderStatus orderStatus) {
		return (orderStatus == null) ? null : orderStatus.getValue();
	}

	@Override
	public OrderStatus convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}

		return Stream.of(OrderStatus.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
