package com.admin.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@EnableJpaRepositories
@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public WebMvcConfigurer configure() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry reg) {
//				reg.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}
}
