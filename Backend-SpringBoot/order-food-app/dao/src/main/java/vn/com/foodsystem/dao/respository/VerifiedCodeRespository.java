package vn.com.foodsystem.dao.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.com.foodsystem.dao.model.VerifiedCodeModel;

public interface VerifiedCodeRespository extends JpaRepository<VerifiedCodeModel, Long>,JpaSpecificationExecutor<VerifiedCodeModel> {
	VerifiedCodeModel findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	void deleteByEmail(String email);
	
	VerifiedCodeModel findByToken(String token);
	
	VerifiedCodeModel findByOtpCode(int otpCode);
}
