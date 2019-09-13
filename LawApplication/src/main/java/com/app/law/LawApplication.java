package com.app.law;

import com.app.law.constant.RoleConstant;
import com.app.law.entity.User;
import com.app.law.service.IUserService;
import com.app.law.service.IprivilegeService;
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
	public CommandLineRunner loadData(IUserService userService, IprivilegeService privilegeService) {
		return (args) -> {
			System.out.println("Init Data");
			if(userService.getAll().size() == 0) {
				List<User> listUser = Arrays.asList(
					new User("123456", "hanguyen@gm.com", "Hà Nguyễn", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "bacnguyen@gm.com", "Bắc Nguyễn", RoleConstant.LUAT_SU, privilegeService.getAll())
				);
				listUser.forEach(userService::createUser);
			}
		};
	}

}
