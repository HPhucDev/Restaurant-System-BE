package vn.com.foodsystem.business.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.com.foodsystem.dao.enums.Gender;
import vn.com.foodsystem.dao.enums.Roles;

/**
 * UserLoginDetails represent as Principal in SecurityContext and for rendering
 * token to UI Do not use data in UserLoginDetails for saving UserModel
 */
public class UserLoginDetails implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String password;

	private String username;

	private String fullName;

	private Gender gender;

	private String phone;

	private Roles role;

	private String pathAvatar;
	
	private Date birthDay;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFulltName(String fullName) {
		this.fullName = fullName;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

}
