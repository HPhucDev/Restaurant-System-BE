package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.business.service.FoodItemService;
import vn.com.foodsystem.business.service.OrderService;
import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.dao.model.OrderDetailID;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.web.dto.OrderDetailDTO;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;

@Configuration
public class OrderDetailConverterService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FoodItemService foodItemService;

	@Autowired
	private OrderService orderService;

	public OrderDetailDTO convertToDTO(OrderDetailModel orderDetailModel) {
		OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetailModel, OrderDetailDTO.class);
		
		return orderDetailDTO;
	}

	public List<OrderDetailDTO> convertToDTO(List<OrderDetailModel> orderDetailModels) {
		if (CollectionUtils.isEmpty(orderDetailModels)) {
			return new ArrayList<>();
		}

		List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
		for (OrderDetailModel orderDetailModel : orderDetailModels) {
			orderDetailDTOs.add(convertToDTO(orderDetailModel));
		}
		return orderDetailDTOs;
	}

	public OrderDetailModel convertToEntity(OrderDetailDTO orderDetailDTO) {

		OrderDetailModel orderDetailModel = modelMapper.map(orderDetailDTO, OrderDetailModel.class);

		OrderModel orderModel = orderService.findOne(orderDetailDTO.getIdOrder());
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not Order!");
		}

		orderDetailModel.setOrder(orderModel);

		FoodItemModel foodItemModel = foodItemService.findOne(orderDetailDTO.getIdFoodItem());

		if (foodItemModel == null) {
			throw new ResourceNotFoundException("Found not FoodItem!");
		}

		orderDetailModel.setFoodItem(foodItemModel);

//		OrderDetailID orderDetailID = new OrderDetailID(orderDetailDTO.getIdOrder(), orderDetailDTO.getId());
//		orderDetailModel.setOrderDetailId(orderDetailID);

		return orderDetailModel;
	}
}
