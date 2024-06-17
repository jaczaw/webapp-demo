package pl.jz.webapp.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.jz.webapp.model.ToDo;
import pl.jz.webapp.serevices.ToDoService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoControllerTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController;

    private List<ToDo> expectedToDos;

    @BeforeEach
    public void setUp() {
        expectedToDos = new ArrayList<>();
        expectedToDos.add(new ToDo(1L, "Task 1", false));
        expectedToDos.add(new ToDo(2L, "Task 2", true));
        expectedToDos.add(new ToDo(3L, "Task 3", false));
    }

    @Test
    void testGetToDos_MultipleThreads() throws InterruptedException, ExecutionException {
        when(toDoService.getToDos()).thenReturn(expectedToDos);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<List<ToDo>>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<List<ToDo>> future = executorService.submit(() -> toDoController.getToDos());
            futures.add(future);
        }

        List<ToDo> actualToDos = new ArrayList<>();
        for (Future<List<ToDo>> future : futures) {
            actualToDos.addAll(future.get());
        }

        executorService.shutdown();

        System.out.println("futures.size()"+futures.size()+"expectedToDos.size()"+expectedToDos.size()+ " actualToDos.size()"+actualToDos.size());

        assertEquals(expectedToDos, actualToDos);
    }

    // Other tests...
}