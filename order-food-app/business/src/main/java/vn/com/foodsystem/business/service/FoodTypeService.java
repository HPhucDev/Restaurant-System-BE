package vn.com.foodsystem.business.service;

import vn.com.foodsystem.dao.enums.FoodTypeName;
import vn.com.foodsystem.dao.model.FoodTypeModel;

public interface FoodTypeService extends IGenericService<FoodTypeModel, Long> {
	public FoodTypeModel findByName(FoodTypeName name);
}
