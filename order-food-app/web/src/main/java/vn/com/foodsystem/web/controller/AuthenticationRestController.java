package vn.com.foodsystem.web.controller;

import java.util.Collections;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.dto.UserLoginDetails;
import vn.com.foodsystem.business.dto.UserResponse;
import vn.com.foodsystem.business.service.UserService;
import vn.com.foodsystem.business.utils.UserLoginDetailsUtil;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.web.dto.AuthRequest;
import vn.com.foodsystem.web.requesthandling.LoginRequest;
import vn.com.foodsystem.web.security.jwt.JwtTokenProvider;
import vn.com.foodsystem.web.security.jwt.LoginProvider;
import vn.com.foodsystem.web.utils.SecurityContextUtil;

@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AuthenticationRestController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

	@Autowired
	private LoginProvider loginProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping(value = "/signin")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			String accessToken = loginProvider.signin(request.getUsername(), request.getPassword());
			UserLoginDetails userDetail = (UserLoginDetails) SecurityContextUtil.getUserDetails();
			UserResponse userResponse = parseDetail(userDetail, accessToken);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity("Invalid username/password", HttpStatus.BAD_REQUEST);
		}
	}
	
	//    @PostMapping(value = "signout")
	//    @ResponseBody
	//    public ResponseEntity signout(String token) {
	//        UserModel user = userService.findByToken(token);
	//        userService.updateToken(user.getUsername(), null);
	//
	//        return new ResponseEntity(HttpStatus.OK);
	//    }

	@PostMapping(value = "/refreshtoken")
	//	public ResponseEntity refreshtoken(@RequestBody String token) {
	public ResponseEntity refreshtoken(@RequestParam String token) {
		String accessToken = jwtTokenProvider.generateRefreshToken(token);
		if (accessToken == null) {
			return new ResponseEntity("invalid token", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(Collections.singletonMap("token", accessToken) , HttpStatus.OK);

	}

	@PostMapping(value = "/auth")
	@ResponseBody
	public ResponseEntity<?> getAuth(@RequestBody AuthRequest request) {

		try {
			// Update current security
			UserLoginDetails old = (UserLoginDetails) SecurityContextUtil.getUserDetails();
			UserModel userModel = userService.findByEmail(old.getUsername());
			UserLoginDetails userLoginDetail = UserLoginDetailsUtil.getUserDetailFrom(userModel);
			Authentication authen = jwtTokenProvider.getAuthentication(userLoginDetail);
			String accessToken = jwtTokenProvider.createToken(authen);
			UserResponse userResponse = parseDetail(userLoginDetail, accessToken);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	private UserResponse parseDetail(UserLoginDetails userLoginDetail, String accessToken) {
		return new UserResponse(userLoginDetail.getUserId(), //
				userLoginDetail.getUsername(), //
				userLoginDetail.getFullName(), //
				userLoginDetail.getRole(), //
				userLoginDetail.getPhone(), //
				userLoginDetail.getPathAvatar(), //
				userLoginDetail.getGender(), //
				userLoginDetail.getBirthDay(), //
				accessToken, //
				"Bearer");
	}
}
