package ru.geekbrains.spring_db_dz_sulagaevdv.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring_db_dz_sulagaevdv.model.Status;
import ru.geekbrains.spring_db_dz_sulagaevdv.model.Task;
import ru.geekbrains.spring_db_dz_sulagaevdv.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    /**
     * добавление задачи
     * @param task
     * @return
     */
    public void addTask(Task task){
        taskRepository.save(task);

    }

    /**
     * Просмотр всех задач
     * @return
     */
    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    /**
     * Просмотр задач по статусу
     * @param status
     * @return
     */
    public List<Task> findTaskByStatus(Status status){
        return taskRepository.findAll().stream().filter(task -> task.getStatus()==status).toList();
    }

    /**
     * Изменение статуса задачи
     * @param id
     * @param newStatus
     * @return
     */
    public Task updateStatusInTask(Long id, Status newStatus){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newStatus);
            return taskRepository.save(task);
        }else {
            throw new IllegalArgumentException("Task not found with ID: " + id);
        }
    }

    /**
     * Удаление задачи
     */
    public void deleteTask(Long id){
        taskRepository.delete(taskRepository.getReferenceById(id));
    }
}
