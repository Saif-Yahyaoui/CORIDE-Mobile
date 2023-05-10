/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class MoyenTransport {
    private int id;
    private double nbPlace;
    private String marque,type,matricule;

    public MoyenTransport() {
    }

    public MoyenTransport(int id, double nbPlace, String marque, String type, String matricule) {
        this.id = id;
        this.nbPlace = nbPlace;
        this.marque = marque;
        this.type = type;
        this.matricule = matricule;
    }

    public MoyenTransport(double nbPlace, String marque, String type, String matricule) {
        this.nbPlace = nbPlace;
        this.marque = marque;
        this.type = type;
        this.matricule = matricule;
    }

    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getNbPlace() {
        return nbPlace;
    }

    public String getMarque() {
        return marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNbPlace(double nbPlace) {
        this.nbPlace = nbPlace;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "MoyenTransport{" + "id=" + id + ", nbPlace=" + nbPlace + ", matricule=" + matricule + ", marque=" + marque + ", type=" + type + '}';
    }

    
    
    
    
}
