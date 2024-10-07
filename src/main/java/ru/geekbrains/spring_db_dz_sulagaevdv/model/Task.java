package ru.geekbrains.spring_db_dz_sulagaevdv.model;

import jakarta.persistence.*;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "date_create")
    private String date_create;

    public Task() {
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        this.date_create = formatter.format(current);
        this.status = Status.NOT_STARTED;
    }
}
