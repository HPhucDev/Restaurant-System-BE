package vn.com.foodsystem.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import vn.com.foodsystem.dao.enums.FoodTypeName;

@Entity
@Table(name = "food_type")
public class FoodTypeModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
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
