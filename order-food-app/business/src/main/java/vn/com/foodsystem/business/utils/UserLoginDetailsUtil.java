package vn.com.foodsystem.business.utils;

import vn.com.foodsystem.business.dto.UserLoginDetails;
import vn.com.foodsystem.dao.model.UserModel;

public class UserLoginDetailsUtil {

    private UserLoginDetailsUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TEMPORARY_USERNAME = "TEMPORARY_USERNAME";
    public static final String TEMPORARY_PASSWORD = "TEMPORARY_PASSWORD";

    public static UserLoginDetails getUserDetailFrom(UserModel userModel) {
        UserLoginDetails userDetails = new UserLoginDetails();
        userDetails.setUserId(userModel.getId());
        userDetails.setUsername(userModel.getEmail());
        userDetails.setPassword(userModel.getPassword());
        userDetails.setFulltName(userModel.getFullName());
        userDetails.setGender(userModel.getGender());
        userDetails.setPhone(userModel.getPhone());
        userDetails.setPathAvatar(userModel.getPathAvatar());
        userDetails.setRole(userModel.getRole());
        userDetails.setBirthDay(userModel.getBirthDay());

        return userDetails;
    }
}
