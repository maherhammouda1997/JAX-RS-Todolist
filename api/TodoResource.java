package fr.m2i.m2ws.api;

import fr.m2i.m2ws.Enum.Urgence;
import jakarta.ws.rs.*;
import fr.m2i.m2ws.model.Todo;
import fr.m2i.m2ws.util.TodoService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("todos")
public class TodoResource {
    private final TodoService service = TodoService.getInstance();

    // Méthode pour afficher la liste des todo en JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> afficher() {
        return service.voirTodos();
    }
    // Méthode pour afficher la liste des todo en XML (en spécifiant le Path /xml)
    @GET
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Todo> afficherEnXML() {
        return service.voirTodos();
    }

    // Méthode pour ajouter un todo à la liste des todos
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response ajouterTodo(@FormParam("urgence") String urgence,
                            @FormParam("titre") String titre,
                            @FormParam("description") String description) {
        Todo todo = new Todo(urgence, titre, description);
        service.ajouterTodo(todo);
        return Response.ok().entity("todo "+ todo.getId() + "ajoutee avec succes !").build();
    }
    // Méthode pour supprimer un todo (par /id)
    @DELETE
    @Path("{id}")
    public Response supprimerParId(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
        if (todo == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        service.supprimerParId(id);
        return Response.status(200).entity("Todo "+ id + " supprime avec succes !").build();
    }
    // Méthode pour mettre à jour un todo (par /id)
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateParId(@PathParam("id") int id,
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
            return Response.status(200).entity("Todo "+ id + " update avec succes !").build();
        }
        return Response.status(404).entity("Todo introuvable").build();
    }



    // Méthode pour afficher un todo en JSON (par /id)
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenirPar(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
        if (todo == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        return Response.ok(todo, MediaType.APPLICATION_JSON).build();
    }

    // Méthode pour afficher un todo en XML (par /Id et en spécifiant le Path /xml)
    @GET
    @Path("{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response obtenirParIdEnXML(@PathParam("id") int id) {
        Todo todo = service.obtenirParId(id);
        if (todo == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        return Response.ok(todo, MediaType.APPLICATION_XML).build();
    }
    // Méthode pour acceder à l'erreur (en spécifiant le PATH /error)
    @GET
    @Path("error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response serveur500() {
        String errorMessage = "Une erreur serveur est produite.";
        return Response.status(500).entity(errorMessage).build();
    }
}
