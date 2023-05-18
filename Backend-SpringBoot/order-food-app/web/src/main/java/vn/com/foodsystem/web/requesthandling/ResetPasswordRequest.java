package vn.com.foodsystem.web.requesthandling;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.com.foodsystem.web.validator.NotFalse;

@NotFalse(messages = { "new password, confirm new password must equal " }, //
		properties = { "newPassword" }, //
		verifiers = { "validPassword" })
public class ResetPasswordRequest {
	@NotBlank(message = "token cannot be blank")
	private String token;
	@NotBlank(message = "Password cannot be blank")
	private String password;
	@NotBlank(message = "Confirm password cannot be blank")
	private String confirmPassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@JsonIgnore
	public Boolean getValidPassword() {
		return Boolean.valueOf(password != null && password.equals(confirmPassword));
	}

}
