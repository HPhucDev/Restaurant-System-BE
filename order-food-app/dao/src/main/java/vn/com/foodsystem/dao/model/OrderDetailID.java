package vn.com.foodsystem.dao.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailID implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private UUID id;
	
	@Column(name = "id_order")
	private Long idOrder;

	@Column(name = "id_food_item")
	private Long idFoodItem;

	public OrderDetailID(Long idOrder, Long idFoodItem) {
		this.idOrder = idOrder;
		this.idFoodItem = idFoodItem;
		this.id=UUID.randomUUID();
	}

	public OrderDetailID() {
		super();
	}


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
