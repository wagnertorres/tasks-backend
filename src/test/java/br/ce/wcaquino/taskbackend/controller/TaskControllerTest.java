package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.now());
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        Task tarefa = new Task();
        tarefa.setTask("Descrição");
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada(){
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.of(2000,01,01));
        tarefa.setTask("Descrição");
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.now());
        tarefa.setTask("Descrição");
        controller.save(tarefa);
        Mockito.verify(taskRepo).save(tarefa);
    }
}