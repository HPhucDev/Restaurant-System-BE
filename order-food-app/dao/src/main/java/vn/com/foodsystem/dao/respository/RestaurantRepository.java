package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.model.RestaurantModel;

public interface RestaurantRepository
		extends JpaRepository<RestaurantModel, Long>, JpaSpecificationExecutor<RestaurantModel> {

}
