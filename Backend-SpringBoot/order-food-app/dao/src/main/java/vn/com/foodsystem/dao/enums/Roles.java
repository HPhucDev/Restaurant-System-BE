package vn.com.foodsystem.dao.enums;

public enum Roles {
    KITCHEN_STAFF("Nhân viên bếp"),//
    DESK_STAFF("Nhân viên phục vụ"),//
    CASHIER_STAFF ("Nhân viên thu ngân"),//
    MANAGER("Quản lý");
	private String value;
	private Roles(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	
}
