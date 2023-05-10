/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MSI
 */
 public class Trajet {

    private int id;
    private String depart;
    private String destination;

    public Trajet(int id, String depart, String destination) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
    }

    public Trajet(String depart, String destination) {
        this.depart = depart;
        this.destination = destination;
    }

   
    public Trajet() {
    }

    // Constructors, getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + '}';
    }
}
