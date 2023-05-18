package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.enums.FoodTypeName;
import vn.com.foodsystem.dao.model.FoodTypeModel;

public interface FoodTypeRepository
		extends JpaRepository<FoodTypeModel, Long>, JpaSpecificationExecutor<FoodTypeModel> {
	public FoodTypeModel findByName(FoodTypeName name);
}
