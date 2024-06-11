package pl.jz.webapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ToDo {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private boolean completed;
}