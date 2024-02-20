package com.springjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.springjava.entity.Role;
import com.springjava.repository.RoleRepository;

@SpringBootApplication
@EnableJpaRepositories("com.springjava.repository.*")
@ComponentScan(basePackages = { "com.springjava.repository.*" })
@EntityScan("com.springjava.entity.*")
@EnableAutoConfiguration
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	// @Bean
    // public CommandLineRunner demo(RoleRepository roleRepo) {
    //     return (args) -> {
    //         Role role=new Role();
    //         role.setRole("ROLE_ADMIN");
    //         roleRepo.save(role);
    //     };
	// }

}
