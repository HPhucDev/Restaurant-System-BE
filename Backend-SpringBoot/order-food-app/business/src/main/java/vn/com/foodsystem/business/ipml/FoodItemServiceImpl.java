package vn.com.foodsystem.business.ipml;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.FoodItemService;
import vn.com.foodsystem.dao.model.FoodItemModel;
import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.dao.respository.FoodItemRepository;

@Service
@Transactional
public class FoodItemServiceImpl extends AbstractService<FoodItemModel, Long> implements FoodItemService {

	private FoodItemRepository foodItemRepository;

	@Autowired
	public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
		super(foodItemRepository);
		this.foodItemRepository = foodItemRepository;
	}

	@Override
	public List<FoodItemModel> findByFoodType(FoodTypeModel foodTypeModel) {
		return foodItemRepository.findByFoodType(foodTypeModel);
	}

	@Override
	public List<FoodItemModel> findByNameContaining(String name) {
		return foodItemRepository.findByNameContaining(name);
	}

	@Override
	public List<FoodItemModel> getAll() {
		return foodItemRepository.getAll();
	}

	

}
