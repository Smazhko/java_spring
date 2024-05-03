package ru.gb.sem7_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthHandler authHandler;

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/css/**", "/favicon.ico", "/img/**", "/products", "/login").permitAll()
			.requestMatchers("/user").hasAnyRole("USER", "ADMIN")
			.requestMatchers("/product_edit").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
		)
		.formLogin(login -> login
				//.loginPage("/login")
				.successHandler(authHandler)
				// .defaultSuccessUrl("/")
				.permitAll())
		.logout(logout -> logout
				.logoutSuccessUrl("/"))
				.csrf().disable();
        return http.build();
    }

	// БИН кодировщика паролей
	@Bean
	PasswordEncoder passwordEncoder() {
		// в зависимости от того, что поддерживает браузер, делегат вернёт b-crypt или s-crypt
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// менеджер пользователй.
	// ТУТ создаём двух пользователей по-умолчанию, которые будут вшиты с базу со старта.
	// определяем логин, пароль и роль
	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		var user1 = User.withUsername("user").password("{noop}password").roles("USER").build();
		var user2 = User.withUsername("admin").password("{noop}password").roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
} 