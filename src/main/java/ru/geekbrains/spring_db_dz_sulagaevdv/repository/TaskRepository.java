package ru.geekbrains.spring_db_dz_sulagaevdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring_db_dz_sulagaevdv.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
