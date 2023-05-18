package vn.com.foodsystem.business.ipml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.utils.UserLoginDetailsUtil;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.dao.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserModel userModel = userRepository.findByEmail(userName);
		log.info("{}",userModel);
		if (userModel == null) {
			throw new UsernameNotFoundException(userName + " is not found");
		}

        Boolean active = Boolean.valueOf(userModel.getStatus().getValue().equalsIgnoreCase("Đã kích hoạt"));

        if (Boolean.FALSE.equals(active)) {
            throw new UsernameNotFoundException("User " + userName + " is disable");
        }

		return UserLoginDetailsUtil.getUserDetailFrom(userModel);
	}

}