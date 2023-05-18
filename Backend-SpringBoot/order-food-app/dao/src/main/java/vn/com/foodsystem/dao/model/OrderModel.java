package vn.com.foodsystem.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import vn.com.foodsystem.dao.enums.TableName;

@Entity
@Table(name = "`order`")
public class OrderModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "total")
	private double total;

	@Column(name = "date_in")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateIn;

	@Column(name = "note")
	private String note;

	@Column(name = "date_out")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOut;

	@Column(name = "`table_name`")
	@Enumerated(EnumType.STRING)
	private TableName tableName;

	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private UserModel user;

	@ManyToOne
	@JoinColumn(name = "id_restaurant", referencedColumnName = "id")
	private RestaurantModel restaurant;

	@OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
	private List<OrderStatusModel> orderStatus;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetailModel> orderDetailModels;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
	private List<OrderPairingModel> orderPairingModels;

	public OrderModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNote() {
		return note;
	}
	

	public List<OrderDetailModel> getOrderDetailModels() {
		return orderDetailModels;
	}

	public void setOrderDetailModels(List<OrderDetailModel> orderDetailModels) {
		this.orderDetailModels = orderDetailModels;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public RestaurantModel getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantModel restaurant) {
		this.restaurant = restaurant;
	}

	public List<OrderStatusModel> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<OrderStatusModel> orderStatus) {
		this.orderStatus = orderStatus;
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

	public TableName getTableName() {
		return tableName;
	}

	public void setTableName(TableName tableName) {
		this.tableName = tableName;
	}

	public List<OrderPairingModel> getOrderPairingModels() {
		return orderPairingModels;
	}

	public void setOrderPairingModels(List<OrderPairingModel> orderPairingModels) {
		this.orderPairingModels = orderPairingModels;
	}
	

}
