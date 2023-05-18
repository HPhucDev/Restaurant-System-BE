package vn.com.foodsystem.dao.enums;

public enum UserStatus {
	ACTIVE("Đã kích hoạt"), //
	IN_ACTIVE("Chưa kích hoạt"),//
	DELETED("Đã xóa");

	private String value;

	private UserStatus(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
}
