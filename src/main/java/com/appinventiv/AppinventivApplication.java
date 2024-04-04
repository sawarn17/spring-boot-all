package com.appinventiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //this is used for to config the AOP
public class AppinventivApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppinventivApplication.class, args);
	}

}
