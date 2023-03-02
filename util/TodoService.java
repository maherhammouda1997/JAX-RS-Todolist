package fr.m2i.m2ws.util;

import fr.m2i.m2ws.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private int idActuel = 1;

    private static TodoService instance = null;

    private TodoService() {
    }

    public static TodoService getInstance() {
        if (instance == null) {
            instance = new TodoService();
        }
        return instance;
    }

    public List<Todo> voirTodos() {
        return new ArrayList<>(todos);
    }

    public void ajouterTodo(Todo todo) {
        todo.setId(idActuel);
        todos.add(todo);
        idActuel++;
    }

    public void supprimerParId(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todos.remove(todo);
                break;
            }
        }
    }

    public Todo updateParId(int id, Todo todoById) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setUrgence(todoById.getUrgence());
                todo.setTitre(todoById.getTitre());
                todo.setDescription(todoById.getDescription());
                return todo;
            }
        }
        return null;
    }

    public Todo obtenirParId(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
}
