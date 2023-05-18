package vn.com.foodsystem.business.ipml;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.OrderDetailService;
import vn.com.foodsystem.dao.model.OrderDetailID;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.respository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailServiceIpml extends AbstractService<OrderDetailModel, Long>
		implements OrderDetailService {

	private OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderDetailServiceIpml(OrderDetailRepository orderDetailRepository) {
		super(orderDetailRepository);
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public List<OrderDetailModel> findDetail(Long idFoodItem,Long idOrder) {
		return orderDetailRepository.findDetail(idFoodItem, idOrder);
	}

}
