package vn.com.foodsystem.business.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.com.foodsystem.dao.enums.Gender;
import vn.com.foodsystem.dao.enums.Roles;

/**
 * Hold current security on UI Do not use any setter in this class because this
 * class is immutable
 */
public class UserResponse {

	private Long userId;
	private String username;
	private String fullName;
	private Roles role;
	private String phone;
	private String pathAvatar;
	private Gender gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date birthDay;
	private String accessToken;
	private String tokenType;

	public UserResponse(Long userId, String username, String fullName, Roles role, String phone, String pathAvatar,
			Gender gender, Date birthDay, String accessToken, String tokenType) {
		super();
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.role = role;
		this.phone = phone;
		this.pathAvatar = pathAvatar;
		this.gender = gender;
		this.birthDay = birthDay;
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
