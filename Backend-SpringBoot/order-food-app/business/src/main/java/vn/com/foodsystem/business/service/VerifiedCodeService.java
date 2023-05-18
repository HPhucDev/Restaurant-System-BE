package vn.com.foodsystem.business.service;

import vn.com.foodsystem.dao.model.VerifiedCodeModel;

public interface VerifiedCodeService extends IGenericService<VerifiedCodeModel, Long> {
	VerifiedCodeModel findByOtpCode(int otpCode);

	VerifiedCodeModel findByToken(String token);
}
