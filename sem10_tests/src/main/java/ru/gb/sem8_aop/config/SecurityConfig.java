package ru.gb.sem8_aop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
			.requestMatchers("/css/**", "/favicon.ico", "/img/**", "/", "/index", "/login").permitAll()
			.requestMatchers("/user", "/product").hasAnyRole("USER")
			.requestMatchers("/admin", "/product","/product_edit").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
		)
		.formLogin(login -> login
				//.loginPage("/login")
				.successHandler(authHandler)
				//.defaultSuccessUrl("/")
				.permitAll())
		.logout(logout -> logout
				.logoutSuccessUrl("/"))
		.csrf().disable();

		// если не отключить защиту CSRF - не проходят пост запросы - выдаётся ошибка 403
		// GPT
		// Если вы используете CSRF защиту в Spring Security, убедитесь, что вы отправляете правильный CSRF
		// токен вместе с вашим POST запросом. В вашем HTML коде формы входа отсутствует CSRF токен.
		// Вам нужно добавить CSRF токен, чтобы ваш POST запрос был успешно аутентифицирован.

		return http.build();
    }

	// БИН кодировщика паролей
	@Bean
	PasswordEncoder passwordEncoder() {
		// в зависимости от того, что поддерживает браузер, делегат вернёт b-crypt или s-crypt
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// Менеджер пользователй.
	// ТУТ создаём двух пользователей по-умолчанию, которые будут вшиты с базу со старта.
	// Определяем логин, пароль и роль
	@Bean
	UserDetailsManager inMemoryUserDetailsManager() {
		var user1 = User.withUsername("user").password("{noop}user").roles("USER").build();
		var user2 = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}
}

/*
Кросс-сайтовая подделка запроса (CSRF) - это вид атаки на веб-приложения, при котором несанкционированный пользователь
отправляет запрос от имени аутентифицированного пользователя. Цель CSRF-атаки - выполнить некоторое действие от имени
пользователя без его согласия.

Spring Security по умолчанию включает защиту от CSRF-атак с помощью генерации и использования CSRF токенов.
CSRF токен - это уникальный токен, который генерируется на стороне сервера и передается клиенту. Клиент должен
отправить этот токен обратно на сервер при выполнении защищенного действия (например, при отправке POST запроса).

Чтобы добавить CSRF токен к вашему запросу, вы можете включить его в вашу форму входа в HTML коде. В Thymeleaf
это можно сделать с помощью атрибута th:csrf. Вот пример:

html
Copy code
<form class="form-signin" method="post" action="/login">
    <!-- Остальные поля формы -->
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
Этот код добавит скрытое поле с CSRF токеном к вашей форме входа. Когда форма отправляется, CSRF токен будет автоматически включен в запрос.

После добавления CSRF токена к вашей форме, запрос должен быть успешно аутентифицирован Spring Security приложением.
 */