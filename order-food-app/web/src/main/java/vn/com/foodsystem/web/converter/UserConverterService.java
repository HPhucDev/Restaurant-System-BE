package vn.com.foodsystem.web.converter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import vn.com.foodsystem.business.config.ApplicationConfig;
import vn.com.foodsystem.business.service.AddressService;
import vn.com.foodsystem.business.service.RestaurantService;
import vn.com.foodsystem.dao.enums.UserStatus;
import vn.com.foodsystem.dao.enums.converter.UserStatusConverter;
import vn.com.foodsystem.dao.model.AddressModel;
import vn.com.foodsystem.dao.model.RestaurantModel;
import vn.com.foodsystem.dao.model.UserModel;
import vn.com.foodsystem.web.dto.UserDTO;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;
import vn.com.foodsystem.web.utils.RequestUtil;


@Configuration
public class UserConverterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ApplicationConfig applicationConfig;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private RestaurantService restaurantService;
    
    public UserDTO convertToDTO(UserModel userModel) {
        UserDTO userDTO = modelMapper.map(userModel, UserDTO.class);
 //       String serverInfo = RequestUtil.getServerInfo(request);

//        if (StringUtils.hasText(userModel.getAvatarGeneratedFilename())) {
//            String avatarPath = String.format("%s/data/%s%s/%s", serverInfo, applicationConfig.getUserUploadPath(), userModel.getId(),
//                    userModel.getAvatarGeneratedFilename());
//            userDTO.setAvatarPath(avatarPath);
//        }

        return userDTO;
    }

    public List<UserDTO> convertToDTO(List<UserModel> userModels) {
        if (CollectionUtils.isEmpty(userModels)) {
            return new ArrayList<>();
        }
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserModel userModel : userModels) {
            userDTOs.add(convertToDTO(userModel));
        }
        return userDTOs;
    }

    public UserModel convertToEntity(UserDTO userDTO) {
        UserModel userModel = modelMapper.map(userDTO, UserModel.class);

//        AddressModel addressModel= addressService.findOne(userDTO.getIdAddress());
//        if(addressModel == null) {
//        	throw new ResourceNotFoundException("Found not address!");
//        }
        RestaurantModel restaurantModel= restaurantService.findOne(userDTO.getIdRestaurant());
        if(restaurantModel == null) {
        	throw new ResourceNotFoundException("Found not restaurant!");
        }
        userModel.setRestaurant(restaurantModel);
        
        return userModel;
    }

}
