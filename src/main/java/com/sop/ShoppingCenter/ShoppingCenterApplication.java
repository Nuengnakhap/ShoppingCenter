package com.sop.ShoppingCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableResourceServer
public class ShoppingCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCenterApplication.class, args);
	}

}
