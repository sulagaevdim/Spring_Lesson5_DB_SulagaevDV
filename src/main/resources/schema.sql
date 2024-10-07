create table tasks (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    status ENUM('NOT_STARTED','IN_PROCESS','COMPLETED') NOT NULL,
    date_create VARCHAR(50) NOT NULL
);