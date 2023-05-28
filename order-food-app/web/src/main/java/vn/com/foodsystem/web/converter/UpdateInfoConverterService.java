package vn.com.foodsystem.web.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.web.dto.FoodItemDTO;
import vn.com.foodsystem.web.dto.OrderDTO;
import vn.com.foodsystem.web.dto.OrderDetailDTO;
import vn.com.foodsystem.web.dto.UserDTO;
import vn.com.foodsystem.web.error.exception.BadRequestException;

@Configuration
public class UpdateInfoConverterService {

	public void convertUserInfo(UserModel userModel, UserDTO userDTO) {
		if (userDTO == null || userModel == null) {
			throw new BadRequestException("Cannot update usesr!");
		}

		if (userDTO.getGender() != null) {
			userModel.setGender(userDTO.getGender());
		}

		if (StringUtils.hasText(userDTO.getFullName())) {
			userModel.setFullName(userDTO.getFullName());
		}

		if (userDTO.getBirthDay() != null) {
			userModel.setBirthDay(userDTO.getBirthDay());
		}

		if (StringUtils.hasText(userDTO.getPhone())) {
			userModel.setPhone(userDTO.getPhone());
		}
		if (userDTO.getStatus() != null) {
			userModel.setStatus(userDTO.getStatus());
		}
		if(userDTO.getRole() !=null) {
			userModel.setRole(userDTO.getRole());
		}
		

	}
	public void convertOrderDetailInfo(OrderDetailModel orderDetailModel,OrderDetailDTO orderDetailDTO) {
		if(orderDetailModel ==null || orderDetailDTO == null) {
			throw new BadRequestException("Cannot update order detail!");
		}
		if(StringUtils.hasText(orderDetailDTO.getNote())) {
			orderDetailModel.setNote(orderDetailDTO.getNote());
		}
		if(orderDetailDTO.getQuantity() != null ) {
			orderDetailModel.setQuantity(orderDetailDTO.getQuantity());
		}
		if(orderDetailDTO.getStatus() != null) {
			orderDetailModel.setStatus(orderDetailDTO.getStatus());
		}
		if(orderDetailDTO.getReasonRefuse() != null) {
			orderDetailModel.setReasonRefuse(orderDetailDTO.getReasonRefuse());
		}
	}
	public void convertFoodItemlInfo(FoodItemModel foodItemModel,FoodItemDTO foodItemDTO) {
		if(foodItemModel == null || foodItemDTO == null) {
			throw new BadRequestException("Cannot update fooditem!");
		}
		if(StringUtils.hasText(foodItemDTO.getDescription())) {
			foodItemModel.setDescription(foodItemDTO.getDescription());
		}
		if(foodItemDTO.getDiscount() != null) {
			foodItemModel.setDiscount(foodItemDTO.getDiscount());
		}
		if(StringUtils.hasText(foodItemDTO.getName())) {
			foodItemModel.setName(foodItemDTO.getName());
		}
		if(foodItemDTO.getPrice() != null) {
			foodItemModel.setPrice(foodItemDTO.getPrice());
		}
		if(foodItemDTO.getStatus() !=null) {
			foodItemModel.setStatus(foodItemDTO.getStatus());
		}
	}
	public void convertOrderInfo(OrderModel orderModel,OrderDTO orderDTO) {
		if(orderModel == null || orderDTO == null) {
			throw new BadRequestException("Cannot update order!");
		}
		if(orderDTO.getTotal() != null) {
			orderModel.setTotal(orderDTO.getTotal());
		}
		if(orderDTO.getDateIn() != null) {
			orderModel.setDateIn(orderDTO.getDateIn());
		}
		if(orderDTO.getDateOut() != null) {
			orderModel.setDateOut(orderDTO.getDateOut());
		}
		if(StringUtils.hasText(orderDTO.getNote())) {
			orderModel.setNote(orderDTO.getNote());
		}
		if(orderDTO.getTableName() != null) {
			orderModel.setTableName(orderDTO.getTableName());
		}
	}

}
