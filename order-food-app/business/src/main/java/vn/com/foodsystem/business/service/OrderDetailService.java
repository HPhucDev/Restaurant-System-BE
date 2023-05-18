package vn.com.foodsystem.business.service;

import java.util.List;

import vn.com.foodsystem.dao.model.OrderDetailModel;

public interface OrderDetailService extends IGenericService<OrderDetailModel, Long> {
	
	public List<OrderDetailModel> findDetail(Long idFoodItem,Long idOrder);
}
