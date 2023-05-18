package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.business.service.FoodTypeService;
import vn.com.foodsystem.business.service.RestaurantService;
import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.dao.model.RestaurantModel;
import vn.com.foodsystem.web.dto.FoodItemDTO;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;

@Configuration
public class FoodItemConverterService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FoodTypeService foodTypeService;

	@Autowired
	private RestaurantService restaurantService;

	public FoodItemDTO convertToDTO(FoodItemModel foodItemModel) {
		FoodItemDTO foodItemDTO = modelMapper.map(foodItemModel, FoodItemDTO.class);
		return foodItemDTO;
	}

	public List<FoodItemDTO> convertToDTO(List<FoodItemModel> foodItemModels) {
		if (CollectionUtils.isEmpty(foodItemModels)) {
			return new ArrayList<>();
		}
		List<FoodItemDTO> foodItemDTOs = new ArrayList<>();
		for (FoodItemModel foodItemModel : foodItemModels) {
			foodItemDTOs.add(convertToDTO(foodItemModel));
		}
		return foodItemDTOs;
	}

	public FoodItemModel convertToEntity(FoodItemDTO foodItemDTO) {
		FoodItemModel fooItemModel = modelMapper.map(foodItemDTO, FoodItemModel.class);

		FoodTypeModel foodTypeModel = foodTypeService.findByName(foodItemDTO.getTypeName());

		if (foodTypeModel == null) {
			throw new ResourceNotFoundException("Found not food type!");
		}
		fooItemModel.setFoodType(foodTypeModel);

		RestaurantModel restaurantModel = restaurantService.findOne(foodItemDTO.getIdRestaurant());

		if (restaurantModel == null) {
			throw new ResourceNotFoundException("Found not restaurantModel!");
		}

		fooItemModel.setRestaurant(restaurantModel);

		return fooItemModel;
	}
}
