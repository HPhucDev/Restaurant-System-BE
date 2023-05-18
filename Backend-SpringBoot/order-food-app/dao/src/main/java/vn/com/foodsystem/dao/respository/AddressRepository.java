package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.model.AddressModel;


public interface AddressRepository extends JpaRepository<AddressModel, Long>,JpaSpecificationExecutor<AddressModel> {
}
