package vn.com.foodsystem.web.requesthandling;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.com.foodsystem.web.validator.NotFalse;

@NotFalse(messages = { "new password, confirm new password must equal " }, //
		properties = { "newPassword" }, //
		verifiers = { "validPassword" })
public class ResetPasswordByUserRequest {
	private Long id;
	@NotBlank(message = "Old Password cannot be blank")
	private String oldPassword;
	@NotBlank(message = "New Password cannot be blank")
	private String newPassword;
	@NotBlank(message = "Confirm password cannot be blank")
	private String confirmPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@JsonIgnore
    public Boolean getValidPassword() {
        return Boolean.valueOf(newPassword != null && newPassword.equals(confirmPassword));
    }

}
