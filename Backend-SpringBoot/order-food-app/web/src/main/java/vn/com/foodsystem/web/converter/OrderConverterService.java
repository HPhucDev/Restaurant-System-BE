package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.business.service.RestaurantService;
import vn.com.foodsystem.business.service.UserService;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.RestaurantModel;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.web.dto.OrderDTO;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;

@Configuration
public class OrderConverterService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	public OrderDTO convertToDTO(OrderModel orderModel) {
		OrderDTO orderDTO = modelMapper.map(orderModel, OrderDTO.class);
		return orderDTO;
	}

	public List<OrderDTO> convertToDTO(List<OrderModel> orderModels) {
		if (CollectionUtils.isEmpty(orderModels)) {
			return new ArrayList<>();
		}

		List<OrderDTO> orderDTOs = new ArrayList<>();
		for (OrderModel orderModel : orderModels) {
			orderDTOs.add(convertToDTO(orderModel));
		}
		return orderDTOs;
	}

	public OrderModel convertToEntity(OrderDTO orderDTO) {
		OrderModel orderModel = modelMapper.map(orderDTO, OrderModel.class);
		UserModel user = userService.findOne(orderDTO.getIdUser());

		if (user == null) {
			throw new ResourceNotFoundException("Found not User!");
		}

		orderModel.setRestaurant(user.getRestaurant());
		orderModel.setUser(user);
		return orderModel;
	}
}
