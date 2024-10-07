package ru.geekbrains.spring_db_dz_sulagaevdv.controller;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring_db_dz_sulagaevdv.model.Status;
import ru.geekbrains.spring_db_dz_sulagaevdv.model.Task;
import ru.geekbrains.spring_db_dz_sulagaevdv.services.TaskService;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    /**
     * для получения списка всех задач нужно отправить Get запрос на адрес localhost:8080/tasks
     */
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.findAllTasks();
    }
    /**
     * для добавления задачи нужно отправить POST запрос на адрес localhost:8080/tasks
     * с body по образцу ниже (можно указать все поля, но достаточно указать поле description,
     * остальное сформируется автоматически):
     * {
     *         "description": "Погладить котика"
     * }
     * @return
     */
    @PostMapping
    public List<Task> addTask(@RequestBody Task task){
        taskService.addTask(task);
        return taskService.findAllTasks();
    }
    /**
     * Просмотр задач по статусу
     * необходимо добавить номер статуса в адресную строку,
     * 1 - не начата
     * 2 - в процессе
     * 3 - завершена
     */
    @GetMapping("/{id}")
    public List<Task> showTaskByStatus(@PathVariable Long id){
        Status status = Status.NOT_STARTED;
        if (id == 2) status = Status.IN_PROCESS;
        if (id == 3) status = Status.COMPLETED;
        Status finalStatus = status;
        return taskService.findAllTasks().stream().filter(task -> task.getStatus() == finalStatus).toList();
    }
    /**
     * изменение статуса задачи
     * необходимо добавить номер статуса и через / id задачи в адресную строку
     * 1 - не начата
     * 2 - в процессе
     * 3 - завершена
     */
    @PutMapping("/{status_id}/{id}")
    public List<Task> updateStatus(@PathVariable int status_id,
                                       @PathVariable Long id){
        Status status = Status.NOT_STARTED;
        if (status_id == 2) status = Status.IN_PROCESS;
        if (status_id == 3) status = Status.COMPLETED;
        Status finalStatus = status;
        taskService.updateStatusInTask(id, finalStatus);
        return taskService.findAllTasks();
    }
    /**
     * удаление задачи по id
     */
    @DeleteMapping("/{id}")
    public List<Task> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return taskService.findAllTasks();
    }
}
