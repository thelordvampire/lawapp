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
					new User("123456", "admin", "Hà Nguyễn", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin1", "Bắc Nguyễn", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin2", "Trần Văn Long", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin3", "Nguyễn Quốc Huy", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin4", "Phạm Huy Hùng", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin5", "Nguyễn Minh Đức", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin6", "Trần Thu Hà", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin7", "Nguyễn Mai Linh", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin8", "Trần Đình Trọng", RoleConstant.LUAT_SU, privilegeService.getAll()),
					new User("123456", "admin9", "Trần Đức Long", RoleConstant.LUAT_SU, privilegeService.getAll())
				);
				listUser.forEach(userService::createUser);
			}
		};
	}

}
