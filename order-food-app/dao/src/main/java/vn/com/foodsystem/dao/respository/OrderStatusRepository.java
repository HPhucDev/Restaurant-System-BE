package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.OrderStatusModel;

public interface OrderStatusRepository
		extends JpaRepository<OrderStatusModel, Long>, JpaSpecificationExecutor<OrderDetailModel> {
	public OrderStatusModel findByOrder(OrderModel orderModel);
}
