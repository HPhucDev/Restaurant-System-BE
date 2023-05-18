package vn.com.foodsystem.dao.enums;

public enum Gender {
	MALE("Nam"), //
	FEMALE("Nữ"), //
	OTHER("Khác");

	private String value;

	private Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
