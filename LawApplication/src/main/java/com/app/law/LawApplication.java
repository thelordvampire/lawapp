package com.app.law;

import com.app.law.constant.RoleConstant;
import com.app.law.entity.User;
import com.app.law.service.IRoleService;
import com.app.law.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LawApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(IUserService userService, IRoleService roleService) {
		return (args) -> {
			System.out.println("Init Data");
			if(userService.getAll().size() == 0) {
				List<User> listUser = Arrays.asList(
					new User("admin", "123456", "hanguyen@gm.com", "Hà Nguyễn", roleService.getRoleById(RoleConstant.ADMIN)),
					new User("admin", "123456", "bacnguyen@gm.com", "Bắc Nguyễn", roleService.getRoleById(RoleConstant.ADMIN))
				);
				listUser.forEach(userService::createUser);
			}
		};
	}

}
