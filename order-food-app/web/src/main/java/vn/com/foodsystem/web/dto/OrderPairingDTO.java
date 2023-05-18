package vn.com.foodsystem.web.dto;

import vn.com.foodsystem.dao.enums.TableName;

public class OrderPairingDTO {
	private Long id;
	
	private TableName tableNameJoin;
	
	private Long idOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TableName getTableNameJoin() {
		return tableNameJoin;
	}

	public void setTableNameJoin(TableName tableNameJoin) {
		this.tableNameJoin = tableNameJoin;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	
	
}
