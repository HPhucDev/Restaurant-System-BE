package vn.com.foodsystem.business.ipml;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.UserService;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.dao.respository.UserRepository;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<UserModel, Long> implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public UserModel findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserModel findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhone(phoneNumber);
	}

	@Override
	public boolean existsByEmail(String email) {
		return false;
	}

	@Override
	public void updateToken(String username, String accessToken) {
		UserModel user = findByEmail(username);
		user.setAccessToken(accessToken);
		userRepository.save(user);
	}

	@Override
	public UserModel findByToken(String token) {
		return userRepository.findByAccessToken(token);
	}

}
