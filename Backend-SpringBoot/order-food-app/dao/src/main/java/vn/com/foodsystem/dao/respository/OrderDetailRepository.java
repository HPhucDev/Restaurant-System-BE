package vn.com.foodsystem.dao.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import vn.com.foodsystem.dao.model.OrderDetailID;
import vn.com.foodsystem.dao.model.OrderDetailModel;

public interface OrderDetailRepository
		extends JpaRepository<OrderDetailModel, Long>, JpaSpecificationExecutor<OrderDetailModel> {
	@Query(value = "SELECT * FROM foodsystem.order_detail o where o.id_food_item = ?1 and o.id_order=?2",nativeQuery = true)
	public List<OrderDetailModel> findDetail(Long idFoodItem,Long idOrder);
}
