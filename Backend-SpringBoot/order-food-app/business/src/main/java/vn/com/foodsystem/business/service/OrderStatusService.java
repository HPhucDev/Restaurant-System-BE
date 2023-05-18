package vn.com.foodsystem.business.service;

import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.OrderStatusModel;

public interface OrderStatusService extends IGenericService<OrderStatusModel, Long> {
	public OrderStatusModel findByOrder(OrderModel orderModel);
}
