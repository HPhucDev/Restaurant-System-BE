package vn.com.foodsystem.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "vn.com.foodsystem.dao")
public class DaoApplication {

	private static Logger logger = LoggerFactory.getLogger(DaoApplication.class);

	public static void main(String[] args) {
		logger.info("Start DAO program");
		SpringApplication.run(DaoApplication.class, args);
		logger.info("Start DAO program END");
	}

}
