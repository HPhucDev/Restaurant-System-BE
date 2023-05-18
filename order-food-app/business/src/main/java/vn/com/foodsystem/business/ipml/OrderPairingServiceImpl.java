package vn.com.foodsystem.business.ipml;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.OrderPairingService;
import vn.com.foodsystem.dao.model.OrderPairingModel;
import vn.com.foodsystem.dao.respository.OrderPairingRespository;

@Service
@Transactional
public class OrderPairingServiceImpl extends AbstractService<OrderPairingModel, Long> implements OrderPairingService {
	private OrderPairingRespository orderPairingRespository;
	
	@Autowired
	public OrderPairingServiceImpl(OrderPairingRespository orderPairingRespository) {
		super(orderPairingRespository);
	}

}
