package vn.com.foodsystem.dao.enums;

public enum OrderStatus {
	NEW("Mới"), //
	WAIT_KICHEN("Đợi bếp"), //
	UPFOOD("Lên món"), //
	ENOIGHFOOD("Đủ món"), //
	PAID("Đã thanh toán"), //
	MERGE("Gộp bàn"),//
	CANCEL("Hủy bỏ");//

	private String value;

	private OrderStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
