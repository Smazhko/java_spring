package ru.gb.sem5_jpa.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.sem5_jpa.model.Task;
import ru.gb.sem5_jpa.model.TaskStatus;
import ru.gb.sem5_jpa.services.TaskService;

import java.util.List;

@RestController
//@RequestMapping("/tasks") // <<<<<<<<<<<<<<<<<<<<<<<<<<<<
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/add_task")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/update")
    public Task updateTaskStatus(@RequestParam Long id, @RequestParam TaskStatus status){
        Task updatedTask = taskService.getTaskById(id);
        updatedTask.setStatus(status);
        return taskService.save(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }
}

/*
Требуется реализовать следующие функции:

- Добавление задачи.
        @PostMapping public Task addTask(@RequestBody Task task)
- Просмотр всех задач.
        @GetMapping public List<Task> getAllTasks()
- Просмотр задач по статусу (например, "завершена", "в процессе", "не начата").
        @GetMapping("/status/{status}") public List<Task> getTasksByStatus(@PathVariable TaskStatus status)
- Изменение статуса задачи.
        @PutMapping("/{id}") public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task)
- Удаление задачи. Подсказка метод в контроллере:
        @DeleteMapping("/{id}") public void deleteTask(@PathVariable Long id)

 */


/*
ДЛЯ ТЕСТИРОВАНИЯ
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