package com.example.permissionRoleProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "")
@EnableTransactionManagement
@SpringBootApplication
public class PermissionRoleProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(PermissionRoleProjectApplication.class, args);
	}

}
