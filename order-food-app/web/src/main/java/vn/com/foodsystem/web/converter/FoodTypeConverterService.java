package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.web.dto.FoodTypeDTO;

@Configuration
public class FoodTypeConverterService {
	@Autowired
	private ModelMapper modelMapper;
	
	

	public FoodTypeDTO convertToDTO(FoodTypeModel foodTypeModel) {
		FoodTypeDTO foodTypeDTO = modelMapper.map(foodTypeModel, FoodTypeDTO.class);
		return foodTypeDTO;
	}

	public List<FoodTypeDTO> convertToDTO(List<FoodTypeModel> foodTypeModels) {
		if (CollectionUtils.isEmpty(foodTypeModels)) {
			return new ArrayList<>();
		}

		List<FoodTypeDTO> foodTypeDTOs = new ArrayList<>();
		for (FoodTypeModel foodTypeModel : foodTypeModels) {
			foodTypeDTOs.add(convertToDTO(foodTypeModel));
		}
		return foodTypeDTOs;
	}

	public FoodTypeModel convertToEntity(FoodTypeDTO postDto) {
		FoodTypeModel post = modelMapper.map(postDto, FoodTypeModel.class);

		return post;
	}
}
