package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.model.OrderPairingModel;

public interface OrderPairingRespository extends JpaRepository<OrderPairingModel, Long>,JpaSpecificationExecutor<OrderPairingModel> {
	
}
