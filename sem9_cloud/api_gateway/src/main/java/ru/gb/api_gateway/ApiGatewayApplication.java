package ru.gb.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

// СМОТРИ СКРИНШОТ В МАТЕРИАЛАХ - НАСТРОЙКИ С ПУТЯМИ
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("sem8_aop",r->r.path("/shop/**")
						.uri("http://localhost:8082/"))
				.route("sem5_jpa",r->r.path("/tasks/**")
						.uri("http://localhost:8081/"))
						.build();}
}
