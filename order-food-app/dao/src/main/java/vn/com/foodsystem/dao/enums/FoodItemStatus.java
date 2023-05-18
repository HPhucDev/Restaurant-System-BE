package vn.com.foodsystem.dao.enums;

import java.util.stream.Stream;

public enum FoodItemStatus {
    ISACTIVE("Kích hoạt"), EXPIRED("Hết hạn"),
    DAMAGE("Hỏng"), NOACTIVE("Chưa kích hoạt");
	private String value;
	private FoodItemStatus(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
	
}
