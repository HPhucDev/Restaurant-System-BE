package vn.com.foodsystem.web.requesthandling;

import java.util.List;

import vn.com.foodsystem.web.dto.OrderDTO;

public class MergeTableRequest {
	
	private static final long serialVersionUID = 1L;
	
	private OrderDTO orderRoot;
	private List<OrderDTO> orderList;
	public OrderDTO getOrderRoot() {
		return orderRoot;
	}
	public void setOrderRoot(OrderDTO orderRoot) {
		this.orderRoot = orderRoot;
	}
	public List<OrderDTO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderDTO> orderList) {
		this.orderList = orderList;
	}
	
}
