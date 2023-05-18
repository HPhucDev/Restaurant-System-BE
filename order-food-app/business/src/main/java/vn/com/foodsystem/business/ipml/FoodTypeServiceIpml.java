package vn.com.foodsystem.business.ipml;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.FoodTypeService;
import vn.com.foodsystem.dao.enums.FoodTypeName;
import vn.com.foodsystem.dao.model.FoodTypeModel;
import vn.com.foodsystem.dao.respository.FoodTypeRepository;

@Service
@Transactional
public class FoodTypeServiceIpml extends AbstractService<FoodTypeModel, Long> implements FoodTypeService {
	private FoodTypeRepository foodTypeRepository;

	@Autowired
	public FoodTypeServiceIpml(FoodTypeRepository foodTypeRepository) {
		super(foodTypeRepository);
		this.foodTypeRepository=foodTypeRepository;
	}

	@Override
	public FoodTypeModel findByName(FoodTypeName name) {
		return foodTypeRepository.findByName(name);
	}

}
