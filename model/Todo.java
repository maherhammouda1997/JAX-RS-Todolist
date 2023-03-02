package fr.m2i.m2ws.model;

import fr.m2i.m2ws.Enum.Urgence;

public class Todo {
    private int id;
    private Urgence urgence;
    private String titre;
    private String description;

    /*public Todo() {}*/

    /*public Todo(Urgence urgence, String titre, String description) {
        this.urgence = urgence;
        this.titre = titre;
        this.description = description;
    }*/

    public Todo(String urgence, String titre, String description) {
        this.urgence = Urgence.valueOf(urgence);
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
