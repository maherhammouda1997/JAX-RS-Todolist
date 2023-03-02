package fr.m2i.m2ws.util;


import fr.m2i.m2ws.model.Todo;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "todoService")
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

    @XmlElement(name = "todos")
    @XmlElementWrapper(name = "liste")
    public List<Todo> getTodos() {
        return todos;
    }

    @XmlElement()
    public int getIdActuel() {
        return idActuel;
    }

    public void setIdActuel(int idActuel) {
        this.idActuel = idActuel;
    }

    public static void setInstance(TodoService instance) {
        TodoService.instance = instance;
    }
}
