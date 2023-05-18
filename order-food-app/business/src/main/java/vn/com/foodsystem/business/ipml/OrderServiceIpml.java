package vn.com.foodsystem.business.ipml;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.OrderService;
import vn.com.foodsystem.dao.enums.TableName;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.respository.OrderRepository;

@Service
@Transactional
public class OrderServiceIpml extends AbstractService<OrderModel, Long> implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceIpml(OrderRepository orderRepository) {
		super(orderRepository);
		this.orderRepository=orderRepository;
	}

	@Override
	public List<OrderModel> getOrderByTable() {
		return orderRepository.getOrderByTable();
	}


	@Override
	public List<OrderModel> findByUser(Long idUser, Date date) {
		return orderRepository.findByUser(idUser, date);
	}

	@Override
	public OrderModel findByOrderDetailModels(OrderDetailModel orderDetailModel) {
		return orderRepository.findByOrderDetailModels(orderDetailModel);
	}

	@Override
	public OrderModel getByid(Long id) {
		return orderRepository.getByid(id);
	}

	@Override
	public List<OrderModel> findByCashier(Date date) {
		return orderRepository.findByCashier(date);
	}

	

	@Override
	public List<OrderModel> findByTableName(TableName tableName) {
		return orderRepository.findByTableName(tableName);
	}

	@Override
	public OrderModel findOrderByTableMerge(String tableName) {
		return orderRepository.findOrderByTableMerge(tableName);
	}

	@Override
	public List<OrderModel> reportByDay(Date date) {
		return orderRepository.reportByDay(date);
	}

	@Override
	public List<OrderModel> reportByYear(int year) {
		return orderRepository.reportByYear(year);
	}

	@Override
	public List<OrderModel> reportByMonth(int year, int month) {
		return orderRepository.reportByMonth(year, month);
	}

	@Override
	public List<OrderModel> reportByQuarter(int year, int quarter) {
		return orderRepository.reportByQuarter(year, quarter);
	}

	


}
