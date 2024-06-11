package pl.jz.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jz.webapp.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
}
