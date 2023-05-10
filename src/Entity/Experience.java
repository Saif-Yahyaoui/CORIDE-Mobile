/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class Experience {
    private int id;
    private String text;
    private int rate;
    
    public Experience() {
    }

    public Experience(int id, String text, int rate) {
        this.id = id;
        this.text = text;
        this.rate = rate;
    }

    public Experience(String text, int rate) {
        this.text = text;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getRate() {
        return rate;
    }

  
    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

  
    @Override
    public String toString() {
        return "Experience{" + "id=" + id + ", text=" + text + ", rate=" + rate + '}';
    }
    
}
