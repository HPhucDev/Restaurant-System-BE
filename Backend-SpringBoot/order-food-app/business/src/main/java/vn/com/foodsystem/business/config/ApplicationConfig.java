package vn.com.foodsystem.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.user.path}")
    private String userUploadPath;

    @Value("${upload.fooditem.path}")
    private String foodItemUploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public String getUserUploadPath() {
        return userUploadPath;
    }

    public String getFoodItemtUploadPath() {
        return foodItemUploadPath;
    }

    public String getFullUserUploadPath() {
        return uploadPath + userUploadPath + "/";
    }

    public String getFullFoodItemUploadPath() {
        return uploadPath + foodItemUploadPath + "/";
    }

}
