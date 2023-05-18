package vn.com.foodsystem.web.dto;

import vn.com.foodsystem.dao.enums.FoodItemStatus;
import vn.com.foodsystem.dao.enums.FoodTypeName;

public class FoodItemDTO {
	private Long id;
	private String name;
	private String urlImages;
	private Double price;
	private Double discount;
	private String description;
	private FoodItemStatus status;
	
	private Long idRestaurant;
	
	private FoodTypeName typeName;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public FoodTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(FoodTypeName typeName) {
		this.typeName = typeName;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}
	

}
