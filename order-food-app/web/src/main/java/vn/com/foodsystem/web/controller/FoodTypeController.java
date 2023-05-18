package vn.com.foodsystem.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.service.FoodTypeService;
import vn.com.foodsystem.web.converter.FoodTypeConverterService;
import vn.com.foodsystem.web.dto.FoodTypeDTO;

@RestController
@RequestMapping("/food-types")
public class FoodTypeController {
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private FoodTypeConverterService foodTypeConverterService;
	
	@GetMapping("/")
	public List<FoodTypeDTO> findAll(){
		return foodTypeConverterService.convertToDTO(foodTypeService.findAll());
	}
	
}
