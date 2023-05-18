package vn.com.foodsystem.dao.enums;

public enum OrderDetailStatus {
	NEW("Mới"),//
	ACCEPT("Chấp nhận"),//
	REFUSE("Từ chối"),//
	FINISH("Hoàn thành");
	
	private String value;
	
	private OrderDetailStatus(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
}
