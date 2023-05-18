package vn.com.foodsystem.web.requesthandling;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderByCashierRequest {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
