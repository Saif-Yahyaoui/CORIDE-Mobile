/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */

public class Poste {

    private int id;
    private String user;
    private String trajet;
    private String vehicule;
    private double prix;
  

    public Poste(int id,String user, String trajet, String vehicule, double prix) {
        this.id = id;
        this.user = user;
        this.trajet = trajet;
        this.vehicule = vehicule;
        this.prix = prix;
       
    }

    public Poste(String user,String trajet, String vehicule, double prix) {
                this.user = user;

        this.trajet = trajet;
        this.vehicule = vehicule;
        
        this.prix = prix;
       
    }

    public Poste() {
    }
 public String getUser() {
        return user;
    }
   

    public int getId() {
        return id;
    }

    public String getTrajet() {
        return trajet;
    }

    public String getVehicule() {
        return vehicule;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

     public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", user=" + user + ", trajet=" + trajet + ", vehicule=" + vehicule + ", prix=" + prix +'}';
    }

 
    
    

}
