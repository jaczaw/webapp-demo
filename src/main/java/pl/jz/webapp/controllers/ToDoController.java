package pl.jz.webapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jz.webapp.model.ToDo;
import pl.jz.webapp.model.dto.AddToDoRequest;
import pl.jz.webapp.serevices.ToDoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDo addToDo(@Valid @RequestBody AddToDoRequest addToDoRequest) {
        return toDoService.addToDo(addToDoRequest.description());
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
    }

    @PatchMapping("/{id}")
    public void updateToDo(@PathVariable Long id, @RequestParam Boolean completed) {
        toDoService.updateToDo(id, completed);
    }
}
