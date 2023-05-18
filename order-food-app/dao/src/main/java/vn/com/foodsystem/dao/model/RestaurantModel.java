package vn.com.foodsystem.dao.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "from_hour")
	private int fromHour;

	@Column(name = "from_minute")
	private int fromMinute;

	@Column(name = "to_hour")
	private int toHour;

	@Column(name = "to_minute")
	private int toMinute;

	@Column(name = "is_open")
	private int isOpen;

	@Column(name = "create_Date")
	private LocalDate createDate;

	public RestaurantModel() {
		super();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getFromHour() {
		return fromHour;
	}

	public void setFromHour(int fromHour) {
		this.fromHour = fromHour;
	}

	public int getFromMinute() {
		return fromMinute;
	}

	public void setFromMinute(int fromMinute) {
		this.fromMinute = fromMinute;
	}

	public int getToHour() {
		return toHour;
	}

	public void setToHour(int toHour) {
		this.toHour = toHour;
	}

	public int getToMinute() {
		return toMinute;
	}

	public void setToMinute(int toMinute) {
		this.toMinute = toMinute;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

}
