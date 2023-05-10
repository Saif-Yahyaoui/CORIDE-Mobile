package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author MSI
 */
public class Reclamation {

    private int id;
    private int user;
    private String rec;
    private String sujet;

    public Reclamation() {
    }

    public Reclamation(int id, int user, String rec, String sujet) {
        this.id = id;
        this.rec = rec;
        this.sujet = sujet;
        this.user = user;
    }

    public Reclamation(int user, String rec, String sujet) {
        this.rec = rec;
        this.sujet = sujet;
        this.user = user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getRec() {
        return rec;
    }

    public String getSujet() {
        return sujet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user=" + user + ", rec=" + rec + ", sujet=" + sujet + '}';
    }

}
