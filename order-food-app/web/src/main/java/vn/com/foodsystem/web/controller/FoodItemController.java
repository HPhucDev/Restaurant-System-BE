package vn.com.foodsystem.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import vn.com.foodsystem.business.config.ApplicationConfig;
import vn.com.foodsystem.business.ipml.FileStorageService;
import vn.com.foodsystem.business.service.FoodItemService;
import vn.com.foodsystem.business.service.FoodTypeService;
import vn.com.foodsystem.dao.enums.FoodTypeName;
import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.web.converter.FoodItemConverterService;
import vn.com.foodsystem.web.converter.UpdateInfoConverterService;
import vn.com.foodsystem.web.dto.FoodItemDTO;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;
import vn.com.foodsystem.web.utils.RequestUtil;

@RequestMapping("api/food-items")
@RestController
public class FoodItemController {

	private static final Logger log = LoggerFactory.getLogger(FoodItemController.class);

	@Autowired
	private FoodItemService foodItemService;

	@Autowired
	private FoodTypeService foodTypeService;

	@Autowired
	private FoodItemConverterService foodItemConverterService;

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UpdateInfoConverterService updateInfoConverterService;
	
	@GetMapping("/{id}")
	public FoodItemDTO findFoodItemById(@PathVariable("id") Long id) {
		FoodItemModel foodItemModel = foodItemService.findOne(id);
		if (foodItemModel == null) {
			throw new ResourceNotFoundException("Found not food!");
		}

		return foodItemConverterService.convertToDTO(foodItemModel);
	}
	@GetMapping
	public List<FoodItemDTO> findAll(){
		List<FoodItemModel> foodItemModels= foodItemService.findAll();
		if(foodItemModels == null) {
			throw new ResourceNotFoundException("Found not food!");
		}
		return foodItemConverterService.convertToDTO(foodItemModels);
	}
	@GetMapping("/by-status")
	public List<FoodItemDTO> findAllByStatus(){
		List<FoodItemModel> foodItemModels= foodItemService.getAll();
		if(foodItemModels == null) {
			throw new ResourceNotFoundException("Found not food!");
		}
		return foodItemConverterService.convertToDTO(foodItemModels);
	}

	@PostMapping
	public FoodItemDTO create(@RequestPart FoodItemDTO foodItemDTO,
			@RequestParam(name = "images", required = false) MultipartFile images) {
		try {
			FoodItemModel foodItemModel = foodItemConverterService.convertToEntity(foodItemDTO);
			foodItemService.save(foodItemModel);

			String urlImg = applicationConfig.getFullFoodItemUploadPath();
			if (images != null && !images.isEmpty()) {
				String filename = images.getOriginalFilename();
				String uploadDir = urlImg + foodItemModel.getId();
				fileStorageService.uploadFile(uploadDir, filename, images);

				String serverInfo = RequestUtil.getServerInfo(request);
				String imgPath = String.format("%s/data/%s%s/%s", serverInfo,
						applicationConfig.getFoodItemtUploadPath(), foodItemModel.getId(), filename);
				foodItemModel.setUrlImages(imgPath);
				foodItemService.save(foodItemModel);
			}
			return foodItemConverterService.convertToDTO(foodItemModel);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cannot create food item");
		}
	}

	@PatchMapping
	public FoodItemDTO update(@RequestParam Long id,@RequestPart FoodItemDTO foodItemDTO,
			@RequestParam(name = "images", required = false) MultipartFile images) {
		FoodItemModel foodItemModel = foodItemService.findOne(id);
		if (foodItemModel == null) {
			throw new ResourceNotFoundException("Found not food item!");
		}
		updateInfoConverterService.convertFoodItemlInfo(foodItemModel, foodItemDTO);
		if(foodItemDTO.getTypeName() !=null) {
			foodItemModel.setFoodType(foodTypeService.findByName(foodItemDTO.getTypeName()));
		}
		foodItemService.save(foodItemModel);
		try {
			String urlImg = applicationConfig.getFullFoodItemUploadPath();
			if (images != null && !images.isEmpty()) {
				String filename = images.getOriginalFilename();
				String uploadDir = urlImg + foodItemModel.getId();
				fileStorageService.uploadFile(uploadDir, filename, images);

				String serverInfo = RequestUtil.getServerInfo(request);
				String imgPath = String.format("%s/data/%s%s/%s", serverInfo,
						applicationConfig.getFoodItemtUploadPath(), foodItemModel.getId(), filename);
				foodItemModel.setUrlImages(imgPath);
				foodItemService.save(foodItemModel);
			}

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cannot update image food item");
		}

		return foodItemConverterService.convertToDTO(foodItemModel);
	}

	@GetMapping("/type/{id}")
	public List<FoodItemDTO> findFoodItemByType(@PathVariable("id") Long id) {
		FoodTypeModel foodTypeModel = foodTypeService.findOne(id);
		if (foodTypeModel == null) {
			throw new ResourceNotFoundException("Found not foodtype!");
		}
		List<FoodItemModel> foodItemModels = foodItemService.findByFoodType(foodTypeModel);
		if (foodItemModels == null) {
			throw new ResourceNotFoundException("Found not food!");
		}

		return foodItemConverterService.convertToDTO(foodItemModels);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) throws IOException {
		FoodItemModel foodItemModel = foodItemService.findOne(id);
		if (foodItemModel == null) {
			throw new ResourceNotFoundException("Found not food!");
		}
		foodItemService.deleteById(id);
		return "Delete success!";
	}

	@GetMapping("/name/{name}")
	public List<FoodItemDTO> findFoodItemByName(@PathVariable("name") String name) {
		List<FoodItemModel> foodItemModels = foodItemService.findByNameContaining(name);

		return foodItemConverterService.convertToDTO(foodItemModels);
	}

	@GetMapping("type-name/{name}")
	public List<FoodItemDTO> findFoodItemByTypeName(@PathVariable("name") FoodTypeName name) {
		FoodTypeModel foodTypeModel = foodTypeService.findByName(name);
		if (foodTypeModel == null) {
			throw new ResourceNotFoundException("Found not foodtype!");
		}
		List<FoodItemModel> foodItemModels = foodItemService.findByFoodType(foodTypeModel);
		if (foodItemModels == null) {
			throw new ResourceNotFoundException("Found not food!");
		}
		return foodItemConverterService.convertToDTO(foodItemModels);
	}

}
