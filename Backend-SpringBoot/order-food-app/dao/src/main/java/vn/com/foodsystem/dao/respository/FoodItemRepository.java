package vn.com.foodsystem.dao.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;

public interface FoodItemRepository
		extends JpaRepository<FoodItemModel, Long>, JpaSpecificationExecutor<FoodItemModel> {
	@Query(value = "SELECT * FROM foodsystem.food_item f where f.status like 'Kích hoạt' and f.id_food_type= ?1", nativeQuery = true)
	List<FoodItemModel> findByFoodType(FoodTypeModel foodTypeModel);

//	@Query(value = "SELECT * FROM foodsystem.food_item where `name` like '%?1%'", nativeQuery = true)
	List<FoodItemModel> findByNameContaining(String name);
	
	@Query(value = "SELECT * FROM foodsystem.food_item f where f.status like 'Kích hoạt'",nativeQuery = true)
	List<FoodItemModel> getAll();
}
