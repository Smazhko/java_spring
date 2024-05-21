package ru.gb.sem11_prometheus;

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

/*
добавляем актуатор в pom.xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

добавляем настройку в application.properties
management.endpoints.web.exposure.include=env, info, health
или
management.endpoints.web.exposure.include=*

добавляем в класс SECURITY CONFIG, чтобы безопасность пустила на точки актуатора.
"/actuator", "/actuator/**").permitAll()

для того, чтобы например в точке health показывалось больше информации
management.endpoint.health.show-details=always

-----------------------------------------
подключаем prometheus, который будет взаимодействовать с актуатором,
и micrometer , который будет собирать информацию с JVM
<dependency>
<groupId>io.micrometer</groupId>
<artifactId>micrometer-core</artifactId>
<version>1.12.2</version>
</dependency>
<dependency>
<groupId>io.micrometer</groupId>
<artifactId>micrometer-registry-prometheus</artifactId>
<version>1.12.2</version>
</dependency>

дописываем настройки, удаляя при этом старые.
management.endpoints.web.exposure.include=metrics, prometheus, health


теперь доступны всё endpoint-ы, в том числе
http://localhost:8080/actuator/prometheus
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/process.cpu.usage

--------------------------------
в настройки самого PROMETHEUS добавляем пару строк (файл prometheus.yml лежит в корневой папке с программой прометеус)
ВАЖНО ! соблюдать отступы, так как это YAML формат

  - job_name: "My-Application"
    metrics_path: "actuator/prometheus"
    static_configs:
      - targets: ["localhost:8080"]

запускаем прометеуз от имени администратора (можно запустить с помощью nssm - тогда он будет работать как служба)
перезапускаем наш проект
заходим на localhost:9090 - на prometheus -> STATUS -> Targets
и видим, что теперь прометеус наблюдает за нашим приложением.

--------------------------------------------
https://grafana.com/grafana/download
GRAFANA проще установить в систему.
При установке в винде она запустится как служба.
 Если устанавливать в докер контейнере, нужны какие-то дополнительные настройки, иначе она не видит прометея.
-> ОШИБКА Post "http://localhost:9090/api/v1/query": dial tcp 127.0.0.1:9090: connect: connection refused -
	There was an error returned querying the Prometheus API.


далее зайти в images -> найти скачанную графану и запустить её
или, если ранее запускалась - зайти в containers -> найти графану и запустить

зайти через браузер на localhost:3000
log/pass admin/admin
Home -> connections -> data sources -> prometheus
Prometheus server URL : http://localhost:9090

можно тонко настроить -> SAVE & TEST

теперь из получаемых метрик можно строить графики и пр.

--------------------------------------------

добавляем свою метрику в классе CONTROLLER
private final Counter addProdCounter = Metrics.counter();
при этом ОЧЕНЬ ВАЖНО !!! Counter, Metrics должны быть импортированы из micrometer

далее  в POST метод addNewProduct добавляем команду addProdCounter.increment();

 */
