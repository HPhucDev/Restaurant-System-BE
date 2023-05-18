package vn.com.foodsystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import vn.com.foodsystem.dao.enums.Gender;
import vn.com.foodsystem.dao.enums.Roles;
import vn.com.foodsystem.dao.enums.UserStatus;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	private Date birthDay;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "status")
	private UserStatus status;

	@Column(name = "path_avatar")
	private String pathAvatar;

	@Column(name = "email")
//	 @Pattern(regexp =
//	 "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$",
//	 message = "Email invalid")
	private String email;

	@Column(name = "phone_number")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
//	private LocalDateTime createDate;
	private Date createDate;

	@Column(name = "access_token")
	private String accessToken;

	@Column(name = "role")
	private Roles role;

	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private RestaurantModel restaurant;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user")
	private List<AddressModel> addresses;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public RestaurantModel getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantModel restaurant) {
		this.restaurant = restaurant;
	}

	public List<AddressModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressModel> addresses) {
		this.addresses = addresses;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}
