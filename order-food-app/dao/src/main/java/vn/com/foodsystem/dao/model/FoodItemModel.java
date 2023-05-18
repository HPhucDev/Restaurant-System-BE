package vn.com.foodsystem.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.com.foodsystem.dao.enums.FoodItemStatus;

@Entity
@Table(name = "food_item")
public class FoodItemModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "`name`")
	private String name;

	@Column(name = "url_images")
	private String urlImages;

	@Column(name = "price")
	private double price;

	@Column(name = "discount")
	private double discount;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private FoodItemStatus status;

	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private RestaurantModel restaurant;

	@ManyToOne
	@JoinColumn(name = "id_food_type")
	private FoodTypeModel foodType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImages() {
		return urlImages;
	}

	public void setUrlImages(String urlImages) {
		this.urlImages = urlImages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FoodItemStatus getStatus() {
		return status;
	}

	public void setStatus(FoodItemStatus status) {
		this.status = status;
	}

	public RestaurantModel getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantModel restaurant) {
		this.restaurant = restaurant;
	}

	public FoodTypeModel getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodTypeModel foodType) {
		this.foodType = foodType;
	}

}
