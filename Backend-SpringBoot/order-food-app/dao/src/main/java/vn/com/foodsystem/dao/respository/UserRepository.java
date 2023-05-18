package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import vn.com.foodsystem.dao.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
	@Query(value = "SELECT * FROM foodsystem.user u where u.email like ?1", nativeQuery = true)
	UserModel findByEmail(String email);

	UserModel findByPhone(String phone);

	boolean existsByEmail(String email);

	public UserModel findByAccessToken(String token);
}
