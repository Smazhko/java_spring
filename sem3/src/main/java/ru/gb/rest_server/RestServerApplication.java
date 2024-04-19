package ru.gb.rest_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

}

/*
domain/User дата класс с конструктором, геттерами и сеттерами, toString и equals

repository/UserRepository - arrayList с User-ами, с методами добавления и удаления

services/UserService - сервис создания нового пользователя
services/DataProcessingSevice - обработка данных из БД - из списка: сортировка, поиск, фильтрация, вычисления и пр.
services/NotificationService - сервис с методами уведомления = печать в консоль принимаемой строки.
services/RegistrationService - объединяет и координирует работу первых трёх сервисов.
	Содержит в себе ссылки на все три: 1. создаёт юзера, 2. добавляет его в список, 3. уведомляет о произошедшем

controllers - связь логики программы с работой HTTP - описание выполнения запросов, т.е.
вызов соответствующих методов у сервисов. Связь с запросами - через аннотации.
/UserController - добавление нового пользователя из тела ПОСТ-запроса; вывод списка всех пользователей.
/TaskController - работа со списком пользователей: сортировка, фильтрация и пр.

 */

/*
Базовое задание
1) В класс RegistrationService добавить поля UserService, NotificationService(добавить в IOC контейнер аннотацией @Autowired или через конструктор класса)
2) Разработать метод processRegistration в котором:
- создается пользователь из параметров метода
- созданный пользователь добавляется в репозиторий
- через notificationService выводится сообщение в консоль
3) В TaskController добавить обработчики filterUsersByAge()(Подсказка @GetMapping("/filter/{age}")) и calculateAverageAge (Подсказка @GetMapping("/calc"))
4) В методе filterUsersByAge параметр age получать через аннотацию @PathVariable

Задание со звездочкой
1) В классе UserController добавить обработчик userAddFromParam извлекающий данные для создания пользователя из параметров HTTP запроса
2) Перенести репозиторий проекта с List<User> на базу данных H2

=============================== Проверка работы ====================================
Для теcтирования проекта использовать программу PostMan:
a) Добавление пользователя - запрос :
метод - POST
адрес - http://localhost:8080/users/body
тело -
{
"name":"King Arthur",
"age":23,
"email":"excalibur@avalon.br"
}
{
"name":"Joan of Ark",
"age": 20,
"email":"freefrance@free.fr"
}
{
"name":"Montezuma II",
"age":32,
"email":"monti@tenochtitlan.mx"
}


b) Получение списка пользователей на сервере - запрос:
метод - GET
адрес - http://localhost:8080/users
c) Проверка сортировки - запрос:
метод - GET
адрес - http://localhost:8080/tasks/sort
d) Проверка фильтрации - запрос:
метод - GET
адрес - http://localhost:8080/tasks/filter/23
e) Проверка среднего арифметического - запрос:
метод - GET
адрес - http://localhost:8080/tasks/calc

http://localhost:8080/users/param?name=123&email=qwe&age=12
 */


/*
>>>>>>>>>>>> @PathVariable
Аннотация @PathVariable используется для связывания значений из URL с параметрами метода. В приведенном выше примере,
число 123 может быть извлечено с помощью @PathVariable.

@GetMapping("/products/{id}")
public Product getProduct(@PathVariable String id) {
    // получить и вернуть продукт по id
}
В этом примере, {id} в URL сопоставляется с параметром id метода getProduct.

>>>>>>>>>>>> @RequestParam
С другой стороны, @RequestParam используется для чтения значений из параметров запроса. Это обычно используется,
когда параметры передаются в URL после символа ?, например, http://website.com/products?id=123.

@GetMapping("/products")
public Product getProduct(@RequestParam String id) {
    // получить и вернуть продукт по id
}

В этом случае, id=123 извлекается из URL и связывается с параметром метода id.
 */
