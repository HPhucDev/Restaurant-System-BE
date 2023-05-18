package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.dao.model.OrderPairingModel;
import vn.com.foodsystem.web.dto.FoodTypeDTO;
import vn.com.foodsystem.web.dto.OrderPairingDTO;


@Configuration
public class OrderPairingConverterService {
	@Autowired
	private ModelMapper modelMapper;
	
	

	public OrderPairingDTO convertToDTO(OrderPairingModel orderPairingModel) {
		OrderPairingDTO orderPairingDTO = modelMapper.map(orderPairingModel, OrderPairingDTO.class);
		return orderPairingDTO;
	}

	public List<OrderPairingDTO> convertToDTO(List<OrderPairingModel> orderPairingModels) {
		if (CollectionUtils.isEmpty(orderPairingModels)) {
			return new ArrayList<>();
		}

		List<OrderPairingDTO> orderPairingDTOs = new ArrayList<>();
		for (OrderPairingModel orderPairingModel : orderPairingModels) {
			orderPairingDTOs.add(convertToDTO(orderPairingModel));
		}
		return orderPairingDTOs;
	}

	public OrderPairingModel convertToEntity(OrderPairingDTO postDto) {
		OrderPairingModel post = modelMapper.map(postDto, OrderPairingModel.class);

		return post;
	}
}
