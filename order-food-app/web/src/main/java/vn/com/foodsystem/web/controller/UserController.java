package vn.com.foodsystem.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import vn.com.foodsystem.business.config.ApplicationConfig;
import vn.com.foodsystem.business.ipml.EmailService;
import vn.com.foodsystem.business.ipml.FileStorageService;
import vn.com.foodsystem.business.service.UserService;
import vn.com.foodsystem.business.service.VerifiedCodeService;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.dao.model.VerifiedCodeModel;
import vn.com.foodsystem.web.converter.UpdateInfoConverterService;
import vn.com.foodsystem.web.converter.UserConverterService;
import vn.com.foodsystem.web.dto.UserDTO;
import vn.com.foodsystem.web.error.exception.BadRequestException;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;
import vn.com.foodsystem.web.requesthandling.ResetPasswordByUserRequest;
import vn.com.foodsystem.web.requesthandling.ResetPasswordRequest;
import vn.com.foodsystem.web.utils.RequestUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserConverterService userConverterService;

	@Autowired
	private VerifiedCodeService verifiedCodeService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UpdateInfoConverterService updateInfoConverterService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private ApplicationConfig applicationConfig; 
	
	@Autowired
    private HttpServletRequest request;
	

	@GetMapping("/users")
	public List<UserModel> findAll() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public UserDTO findUserById(@PathVariable("id") Long id) {
		log.info("{}", userService.findOne(id));
		UserModel userModel = userService.findOne(id);
		if (userModel == null) {
			throw new ResourceNotFoundException("Found not user!");
		}
		return userConverterService.convertToDTO(userModel);
	}
	@PostMapping
	public UserDTO create(@RequestPart UserDTO userDTO,@RequestParam(name = "avatar",required = false) MultipartFile avatar ) {
		UserModel userModel= userService.findByEmail(userDTO.getEmail());
		if(userModel != null) {
			throw new BadRequestException("Email already exists!");
		}
		try {
			userModel= userConverterService.convertToEntity(userDTO);
			userModel.setId(null);
			userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
			userModel.setCreateDate(new Date());
			userService.save(userModel);
			String urlImg=applicationConfig.getFullUserUploadPath();
			if(avatar != null && !avatar.isEmpty()) {
				String filename = avatar.getOriginalFilename();
				String uploadDir = urlImg + userModel.getId();
				fileStorageService.uploadFile(uploadDir, filename, avatar);
				
				String serverInfo = RequestUtil.getServerInfo(request);
				String avatarPath= String.format("%s/data/%s%s/%s", serverInfo, applicationConfig.getUserUploadPath(), userModel.getId(),filename);
				userModel.setPathAvatar(avatarPath);
				userService.save(userModel);
			}
			return userConverterService.convertToDTO(userModel);
			
		} catch (Exception e) {
			 throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cannot create user");
		}
	} 
	
	@PatchMapping
	public UserDTO update(@RequestParam Long id, @RequestPart UserDTO userDTO,@RequestParam(name = "avatar",required = false) MultipartFile avatar) {
		UserModel userModel= userService.findOne(id);
		if(userModel == null) {
			throw new ResourceNotFoundException("Found not user!");
		}
		updateInfoConverterService.convertUserInfo(userModel, userDTO);
		userService.save(userModel);
		
		try {
			String urlImg=applicationConfig.getFullUserUploadPath();
			if(avatar != null && !avatar.isEmpty()) {
				String filename = avatar.getOriginalFilename();
				String uploadDir = urlImg + userModel.getId();
				fileStorageService.uploadFile(uploadDir, filename, avatar);
				
				String serverInfo = RequestUtil.getServerInfo(request);
				String avatarPath= String.format("%s/data/%s%s/%s", serverInfo, applicationConfig.getUserUploadPath(), userModel.getId(),filename);
				userModel.setPathAvatar(avatarPath);
				userService.save(userModel);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cannot update user");
		}
		return userConverterService.convertToDTO(userModel);
	}
	

	@PostMapping("/forgot-pass/{email}")
	public String forgotPasswordByMail(@PathVariable("email") String email) {
		UserModel user = userService.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("Account not exist!");
        }
		VerifiedCodeModel verifiedCodeModel = new VerifiedCodeModel();
		LocalDateTime date = LocalDateTime.now();
		verifiedCodeModel.setCreateDate(date);
		verifiedCodeModel.setExpireDate(date.plusMinutes(30));
		Random random = new Random();
		int otpCode = random.nextInt((999999 - 100000) + 1 + 100000);
		verifiedCodeModel.setOtpCode(otpCode);
		verifiedCodeModel.setEmail(email);
		verifiedCodeService.save(verifiedCodeModel);
		emailService.sendResetPasswordMail(email, otpCode);

		return "Send eamil successful!";
	}
	@PostMapping("/forgot-pass/confirm-otp/{otp}")
    public Map<String, String> confirmForgotPassword(@PathVariable("otp") int otp) {
        VerifiedCodeModel verifiedCodeModel = verifiedCodeService.findByOtpCode(otp);
         if (verifiedCodeModel == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token!");
        }
        String token = UUID.randomUUID().toString();
        verifiedCodeModel.setToken(token);
        verifiedCodeService.save(verifiedCodeModel);
        
        return Collections.singletonMap("token", token);
    }

    @PostMapping("/forgot-pass/reset-password")
    public String resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        VerifiedCodeModel verifiedCodeModel = verifiedCodeService.findByToken(resetPasswordRequest.getToken());
        if (verifiedCodeModel == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token!");
        }
        UserModel user = userService.findByEmail(verifiedCodeModel.getEmail());
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        userService.save(user);
        try {
            verifiedCodeService.delete(verifiedCodeModel);
        } catch (IOException e) {
            log.error("{}", e.getMessage());
        }

        return "Reset password successful!";
    }
    @PostMapping("/reset-password")
    public UserDTO resetPasswordByUser(@Valid @RequestBody ResetPasswordByUserRequest resetPasswordByUserRequest) {
    	UserModel userModel= userService.findOne(resetPasswordByUserRequest.getId());
    	if(userModel == null ) {
    		throw new ResourceNotFoundException("Found not user!");
    	}
    	if(!passwordEncoder.matches(resetPasswordByUserRequest.getOldPassword(), userModel.getPassword())) {
    		throw new BadRequestException("Password old incorrect!");
    	}
    	userModel.setPassword(passwordEncoder.encode(resetPasswordByUserRequest.getNewPassword()));
    	userService.save(userModel);
    	return userConverterService.convertToDTO(userModel);
    }

}
