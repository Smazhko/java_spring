package ru.gb.sem5_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sem5JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sem5JpaApplication.class, args);
	}

}

/*
ДЛЯ ТЕСТИРОВАНИЯ в POSTMAN

GET localhost:8080/
GET localhost:8080/1
GET localhost:8080/10

POST localhost:8080/add_task
{
    "description": "сделать уроки",
    "status": "IN_PROGRESS",
    "createTime": "2000-04-20T15:30:45"
}

GET localhost:8080/status/IN_PROGRESS
GET localhost:8080/status/NOT_START ---->>> выдаёт 400 - BadRequest

PUT localhost:8080/update?id=2&status=COMPLETED

DELETE localhost:8080/6

 */

/*
Базовое задание:
Условие:
Вам предстоит создать приложение для управления списком задач с использованием Spring Boot и Spring Data JPA.

Требуется реализовать следующие функции:

- Добавление задачи.
Подсказка метод в контроллере: @PostMapping public Task addTask(@RequestBody Task task)
- Просмотр всех задач.
Подсказка метод в контроллере: @GetMapping public List<Task> getAllTasks()
- Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
Подсказка метод в контроллере: @GetMapping("/status/{status}") public List<Task> getTasksByStatus(@PathVariable TaskStatus status)
- Изменение статуса задачи.
Подсказка метод в контроллере: @PutMapping("/{id}") public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)
- Удаление задачи. Подсказка метод в контроллере:
@DeleteMapping("/{id}") public void deleteTask(@PathVariable Long id)

Репозиторий подсказка public interface TaskRepository extends JpaRepository<Task, Long>

Структура задачи(класс Task):
- ID (автоинкрементное)(тип Long)
- Описание (не может быть пустым)(тип String)
- Статус (одно из значений: "не начата", "в процессе", "завершена")(Тип TaskStatus )
- Дата создания (автоматически устанавливается при создании задачи)(Тип LocalDateTime)

Подсказка понадобится энумератор:
enum TaskStatus {
NOT_STARTED, IN_PROGRESS, COMPLETED;
}

Задание со звездочкой:
Cоздать серверное приложение, которое может получать файлы, загружаемые по протоколу HTTP из нескольких частей.
Для основы проекта взять пример: https://spring.io/guides/gs/uploading-files
 */
