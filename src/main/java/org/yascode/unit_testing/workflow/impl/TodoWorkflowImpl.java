package org.yascode.unit_testing.workflow.impl;

import org.yascode.unit_testing.service.TodoService;
import org.yascode.unit_testing.workflow.TodoWorkflow;

import java.util.List;

public class TodoWorkflowImpl implements TodoWorkflow {
    private final TodoService todoService;

    public TodoWorkflowImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public List<String> retrieveTodosRelatedToJava(String user) {
        return todoService.retrieveTodos(user).stream()
                .filter(todo -> todo.contains("Java"))
                .toList();
    }
}
