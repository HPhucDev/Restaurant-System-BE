package vn.com.foodsystem.dao.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vn.com.foodsystem.dao.enums.OrderDetailStatus;

@Converter(autoApply = true)
public class OrderDetailStatusConveter implements AttributeConverter<OrderDetailStatus, String>{

	@Override
	public String convertToDatabaseColumn(OrderDetailStatus orderDetailStatus) {
		return (orderDetailStatus == null) ? null : orderDetailStatus.getValue();
	}

	@Override
	public OrderDetailStatus convertToEntityAttribute(String value) {
		if (value == null) {
			return null;
		}

		return Stream.of(OrderDetailStatus.values()).filter(c -> c.getValue().equals(value)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
