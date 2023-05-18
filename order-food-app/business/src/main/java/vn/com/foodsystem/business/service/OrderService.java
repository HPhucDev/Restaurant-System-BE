package vn.com.foodsystem.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import vn.com.foodsystem.dao.enums.TableName;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;

public interface OrderService extends IGenericService<OrderModel, Long> {
	public List<OrderModel> getOrderByTable();
	public List<OrderModel> findByUser(Long idUser,Date date);
	public OrderModel findByOrderDetailModels(OrderDetailModel orderDetailModel);
	public OrderModel getByid(Long id);
	public List<OrderModel> findByCashier(@Param("date") Date date);
	public OrderModel findOrderByTableMerge(String tableName);
	
	public List<OrderModel> findByTableName(TableName tableName);
	
	public List<OrderModel> reportByDay(Date date);
	public List<OrderModel> reportByYear(int year);
	public List<OrderModel> reportByMonth(int year ,int month);
	public List<OrderModel> reportByQuarter(int year, int quarter);
}
