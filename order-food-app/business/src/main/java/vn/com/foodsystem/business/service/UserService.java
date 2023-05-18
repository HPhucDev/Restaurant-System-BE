package vn.com.foodsystem.business.service;

import vn.com.foodsystem.dao.model.UserModel;

public interface UserService extends IGenericService<UserModel, Long> {
	UserModel findByEmail(String email);

	UserModel findByPhoneNumber(String phoneNumber);

	boolean existsByEmail(String email);

	public void updateToken(String username, String accessToken);

	public UserModel findByToken(String token);

}
