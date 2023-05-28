package vn.com.foodsystem.web.dto;

import vn.com.foodsystem.dao.enums.OrderDetailStatus;

public class OrderDetailDTO {
	private Long id;
	private Long idFoodItem;
	private Long idOrder;
	private Double quantity;
	private String note;
	private String reasonRefuse;
	private OrderDetailStatus status;
	
	private FoodItemDTO foodItemModel;
	
	public Long getIdFoodItem() {
		return idFoodItem;
	}

	public void setIdFoodItem(Long idFoodItem) {
		this.idFoodItem = idFoodItem;
	}

	public String getReasonRefuse() {
		return reasonRefuse;
	}

	public void setReasonRefuse(String reasonRefuse) {
		this.reasonRefuse = reasonRefuse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderDetailStatus getStatus() {
		return status;
	}

	public void setStatus(OrderDetailStatus status) {
		this.status = status;
	}

	public FoodItemDTO getFoodItemModel() {
		return foodItemModel;
	}

	public void setFoodItemModel(FoodItemDTO foodItemModel) {
		this.foodItemModel = foodItemModel;
	}

	
	
	
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getFoodUrlImages() {
//		return foodUrlImages;
//	}
//
//	public void setFoodUrlImages(String foodUrlImages) {
//		this.foodUrlImages = foodUrlImages;
//	}
//
//	public Double getFoodPrice() {
//		return foodPrice;
//	}
//
//	public void setFoodPrice(Double foodPrice) {
//		this.foodPrice = foodPrice;
//	}
//
//	public Double getFoodDiscount() {
//		return foodDiscount;
//	}
//
//	public void setFoodDiscount(Double foodDiscount) {
//		this.foodDiscount = foodDiscount;
//	}
//
//	public String getFoodDescription() {
//		return foodDescription;
//	}
//
//	public void setFoodDescription(String foodDescription) {
//		this.foodDescription = foodDescription;
//	}
//
//	public FoodItemStatus getFoodStatus() {
//		return foodStatus;
//	}
//
//	public void setFoodStatus(FoodItemStatus foodStatus) {
//		this.foodStatus = foodStatus;
//	}

	

}
