package vn.com.foodsystem.business.ipml;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.RestaurantService;
import vn.com.foodsystem.dao.model.RestaurantModel;
import vn.com.foodsystem.dao.respository.RestaurantRepository;

@Service
@Transactional
public class RestaurantServiceImpl extends AbstractService<RestaurantModel, Long> implements RestaurantService {
	private RestaurantRepository restaurantRepository;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		super(restaurantRepository);
		this.restaurantRepository = restaurantRepository;
	}

}
