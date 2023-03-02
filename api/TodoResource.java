package fr.m2i.m2ws.api;


import fr.m2i.m2ws.Enum.Urgence;
import jakarta.ws.rs.*;
import fr.m2i.m2ws.model.Todo;
import fr.m2i.m2ws.util.TodoService;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("todos")
public class TodoResource {
    private final TodoService service = TodoService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> afficher() {
        return service.voirTodos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Todo ajouterTodo(@FormParam("urgence") String urgence,
                        @FormParam("titre") String titre,
                        @FormParam("description") String description) {
        Todo todo = new Todo(urgence, titre, description);
        service.ajouterTodo(todo);
        return todo;
    }

    @DELETE
    @Path("{id}")
    public String supprimerParId(@PathParam("id") int id) {
        service.supprimerParId(id);
        return " todo " + id + " supprime avec succes ! ";
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Todo updateParId(@PathParam("id") int id,
                               @FormParam("urgence") String urgence,
                               @FormParam("titre") String titre,
                               @FormParam("description") String description) {
        Todo todoToUpdate = service.obtenirParId(id);
        if (todoToUpdate != null) {
            if (urgence != null) {
                todoToUpdate.setUrgence(Urgence.valueOf(urgence));
            }
            if (titre != null) {
                todoToUpdate.setTitre(titre);
            }
            if (description != null) {
                todoToUpdate.setDescription(description);
            }
            service.updateParId(id, todoToUpdate);
        }
        return todoToUpdate;
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo obtenirParId(@PathParam("id") int id) {
        return service.obtenirParId(id);
    }
    }

