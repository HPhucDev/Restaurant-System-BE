package vn.com.foodsystem.business.ipml;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.OrderStatusService;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.OrderStatusModel;
import vn.com.foodsystem.dao.respository.OrderStatusRepository;

@Service
@Transactional
public class OrderStatusServiceIpml extends AbstractService<OrderStatusModel, Long> implements OrderStatusService {

	private OrderStatusRepository orderStatusRepository;

	@Autowired
	public OrderStatusServiceIpml(OrderStatusRepository orderStatusRepository) {
		super(orderStatusRepository);
		this.orderStatusRepository=orderStatusRepository;
	}

	@Override
	public OrderStatusModel findByOrder(OrderModel orderModel) {
		return orderStatusRepository.findByOrder(orderModel);
	}

}
