package vn.com.foodsystem.web.dto;

import vn.com.foodsystem.dao.enums.FoodTypeName;

public class FoodTypeDTO {
	private Long id;
	private FoodTypeName name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodTypeName getName() {
		return name;
	}

	public void setName(FoodTypeName name) {
		this.name = name;
	}

}
