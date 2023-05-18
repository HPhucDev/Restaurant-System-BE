package vn.com.foodsystem.web.dto;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import vn.com.foodsystem.dao.enums.TableName;
import vn.com.foodsystem.dao.model.OrderDetailModel;

public class OrderDTO {
	private Long id;
	private Double total;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date dateIn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date dateOut;
	private String note;
	private TableName tableName;
	// User
	private Long idUser;
	
	private List<OrderStatusDTO> orderStatus;
	
	private List<OrderDetailDTO> orderDetailModels;
	
	private List<OrderPairingDTO> orderPairingModels;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<OrderDetailDTO> getOrderDetailModels() {
		return orderDetailModels;
	}

	public void setOrderDetailModels(List<OrderDetailDTO> orderDetailModels) {
		this.orderDetailModels = orderDetailModels;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public TableName getTableName() {
		return tableName;
	}

	public void setTableName(TableName tableName) {
		this.tableName = tableName;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<OrderStatusDTO> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<OrderStatusDTO> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderPairingDTO> getOrderPairingModels() {
		return orderPairingModels;
	}

	public void setOrderPairingModels(List<OrderPairingDTO> orderPairingModels) {
		this.orderPairingModels = orderPairingModels;
	}

	
}
