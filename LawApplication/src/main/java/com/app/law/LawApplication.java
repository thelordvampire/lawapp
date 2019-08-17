package com.app.law;

import com.app.law.entity.User;
import com.app.law.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LawApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(UserService userService) {
//		return (args) -> {
//			System.out.println("Init Data");
//			User user = new User();
//			user.setUsername("admin");
//			user.setPassword("123456");
//			user.setEmail("admin@gm.com");
//			userService.createUser(user);
//		};
//	}

}
