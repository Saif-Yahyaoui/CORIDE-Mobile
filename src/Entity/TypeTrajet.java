/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.List;

/**
 *
 * @author MSI
 */

public class TypeTrajet { 
    
    private int id;
    private String typet;
    private List<Trajet> trajets;
    public static TypeTrajet valueOf(String toString) {
        TypeTrajet typeTrajet = new TypeTrajet();
        typeTrajet.setTypet(toString);
        return typeTrajet;
    }
    
    
    // Constructors, getters and setters
    
    public int getId() {
        return id;
    }

    public TypeTrajet(String typet, List<Trajet> trajets) {
        this.typet = typet;
        this.trajets = trajets;
    }

    public TypeTrajet(int id, String typet, List<Trajet> trajets) {
        this.id = id;
        this.typet = typet;
        this.trajets = trajets;
    }

    public TypeTrajet() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypet() {
        return typet;
    }

    public void setTypet(String typet) {
        this.typet = typet;
    }

    public List<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }

    @Override
    public String toString() {
        return "TypeTrajet{" + "id=" + id + ", typet=" + typet + ", trajets=" + trajets + '}';
    }
    
}

