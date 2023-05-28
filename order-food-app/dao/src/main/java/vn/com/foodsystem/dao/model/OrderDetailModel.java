package vn.com.foodsystem.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.com.foodsystem.dao.enums.OrderDetailStatus;

@Entity
@Table(name = "order_detail")
public class OrderDetailModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_food_item")
	private FoodItemModel foodItem;

	@ManyToOne
	@JoinColumn(name = "id_order")
	private OrderModel order;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "`note`")
	private String note;
	
	@Column(name = "reason_refuse")
	private String reasonRefuse;

	@Column(name = "`status`")
	private OrderDetailStatus status;

	public OrderDetailModel() {
		super();
	}

	
	
//	public OrderDetailID getOrderDetailId() {
//		return orderDetailId;
//	}
//
//	public void setOrderDetailId(OrderDetailID orderDetailId) {
//		this.orderDetailId = orderDetailId;
//	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public FoodItemModel getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItemModel foodItem) {
		this.foodItem = foodItem;
	}

	public OrderModel getOrder() {
		return order;
	}

	public OrderDetailStatus getStatus() {
		return status;
	}

	public void setStatus(OrderDetailStatus status) {
		this.status = status;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



	public String getReasonRefuse() {
		return reasonRefuse;
	}

	public void setReasonRefuse(String reasonRefuse) {
		this.reasonRefuse = reasonRefuse;
	}

}
