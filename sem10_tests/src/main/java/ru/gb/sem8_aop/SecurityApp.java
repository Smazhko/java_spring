package ru.gb.sem8_aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SecurityApp {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApp.class, args);
	}

}
