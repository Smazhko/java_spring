package ru.gb.sem5_jpa.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.sem5_jpa.exceptions.TaskNotFoundException;
import ru.gb.sem5_jpa.model.Task;
import ru.gb.sem5_jpa.model.TaskStatus;
import ru.gb.sem5_jpa.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRep;

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(){
        return taskRep.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id){
        return taskRep.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public Task addTask(Task newTask) {
        return taskRep.save(newTask);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(TaskStatus status){
        return taskRep.findByStatus(status);
    }

    @Transactional
    public Task save(Task task){
        return taskRep.save(task);
    }

    @Transactional
    public void deleteTaskById(Long id){
        taskRep.deleteById(id);
    }
}


/*
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

 */

