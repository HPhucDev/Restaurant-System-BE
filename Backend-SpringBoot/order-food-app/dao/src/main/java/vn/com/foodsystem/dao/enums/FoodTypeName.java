package vn.com.foodsystem.dao.enums;

public enum FoodTypeName {
	SALAD("Gỏi"), //
	HOT_POT("Lẩu"), //
	GRILLED("Nướng"), //
	DRINK("Nước");

	private String value;

	private FoodTypeName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
