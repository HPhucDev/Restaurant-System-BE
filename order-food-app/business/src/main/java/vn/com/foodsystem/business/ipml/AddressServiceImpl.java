package vn.com.foodsystem.business.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.foodsystem.business.service.AddressService;
import vn.com.foodsystem.dao.model.AddressModel;
import vn.com.foodsystem.dao.respository.AddressRepository;

@Service
public class AddressServiceImpl extends AbstractService<AddressModel, Long> implements AddressService {
	private AddressRepository addressRepository;
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		super(addressRepository);
		this.addressRepository=addressRepository;
	}

}
