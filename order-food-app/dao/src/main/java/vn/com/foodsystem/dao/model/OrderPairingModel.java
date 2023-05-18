package vn.com.foodsystem.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.com.foodsystem.dao.enums.TableName;


@Table(name = "order_pairing")
@Entity
public class OrderPairingModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "`table_name_join`")
	@Enumerated(EnumType.STRING)
	private TableName tableNameJoin;
	
	@ManyToOne
	@JoinColumn(name = "id_order")
	private OrderModel order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public OrderPairingModel() {
		super();
	}

	public OrderPairingModel(TableName tableNameJoin, OrderModel order) {
		super();
		this.tableNameJoin = tableNameJoin;
		this.order = order;
	}

	public TableName getTableNameJoin() {
		return tableNameJoin;
	}

	public void setTableNameJoin(TableName tableNameJoin) {
		this.tableNameJoin = tableNameJoin;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}
	
	
}
