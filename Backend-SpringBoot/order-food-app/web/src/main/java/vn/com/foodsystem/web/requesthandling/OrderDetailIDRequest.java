package vn.com.foodsystem.web.requesthandling;

import javax.validation.constraints.NotBlank;

public class OrderDetailIDRequest {
	@NotBlank(message = "Order id cannot be empty ")
	private Long idOrder;
	
	@NotBlank(message = "FoodItem id cannot be empty !")
	private Long idFoodItem;

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdFoodItem() {
		return idFoodItem;
	}

	public void setIdFoodItem(Long idFoodItem) {
		this.idFoodItem = idFoodItem;
	}
	
	
	
}
