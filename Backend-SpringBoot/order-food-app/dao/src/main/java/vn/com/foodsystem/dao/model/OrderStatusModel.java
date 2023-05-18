package vn.com.foodsystem.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.com.foodsystem.dao.enums.OrderStatus;

@Entity
@Table(name = "order_status")
public class OrderStatusModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private OrderStatus name;

	@Column(name = "datetime_status")
	private Date dateTimeStatus;
	
	@ManyToOne
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private OrderModel order;

	public OrderStatusModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getName() {
		return name;
	}

	public void setName(OrderStatus name) {
		this.name = name;
	}

	public Date getDateTimeStatus() {
		return dateTimeStatus;
	}

	public void setDateTimeStatus(Date dateTimeStatus) {
		this.dateTimeStatus = dateTimeStatus;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

}
