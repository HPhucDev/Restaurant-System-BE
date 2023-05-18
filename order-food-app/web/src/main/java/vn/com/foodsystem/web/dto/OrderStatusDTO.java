package vn.com.foodsystem.web.dto;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.com.foodsystem.dao.enums.OrderStatus;

public class OrderStatusDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private OrderStatus status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date dateTimeStatus;

	public Long getId() {
		return id;
	}

	

	public OrderStatus getStatus() {
		return status;
	}



	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setName(OrderStatus name) {
		this.status = name;
	}

	public Date getDateTimeStatus() {
		return dateTimeStatus;
	}

	public void setDateTimeStatus(Date dateTimeStatus) {
		this.dateTimeStatus = dateTimeStatus;
	}
	
	
}
