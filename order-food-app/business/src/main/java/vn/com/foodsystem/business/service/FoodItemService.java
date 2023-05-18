package vn.com.foodsystem.business.service;

import java.util.List;

import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;

public interface FoodItemService extends IGenericService<FoodItemModel, Long> {
	List<FoodItemModel> findByFoodType(FoodTypeModel foodTypeModel);

	List<FoodItemModel> findByNameContaining(String name);
	
	List<FoodItemModel> getAll();
}
