package org.yascode.unit_testing.workflow.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yascode.unit_testing.service.TodoService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoWorkflowImplTest {
    @Mock
    TodoService todoServiceMock;

    TodoWorkflowImpl todoBusiness;


    @BeforeEach
    public void setUp() {
        todoBusiness = new TodoWorkflowImpl(todoServiceMock);
    }

    @Test
    void testRetrieveTodosRelatedToJava_with_listFilled() {
        List<String> todos = Arrays.asList("Learn Spring", "Learn Java", "Learn Spring Boot");

        when(todoServiceMock.retrieveTodos("User")).thenReturn(todos);

        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToJava("User");

        assertEquals(1, filteredTodos.size());
    }

    @Test
    void testRetrieveTodosRelatedToJava_with_emptyList() {
        List<String> todos = Arrays.asList();

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToJava("Dummy");

        assertEquals(0, filteredTodos.size());
    }
}