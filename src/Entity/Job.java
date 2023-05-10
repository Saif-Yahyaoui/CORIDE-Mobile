/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class Job {
    private int id;
    private String title, description,location;
    private int salary;

    public Job() {
    }

    public Job(int id, String title, String description, String location, int salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
    }

    public Job(String title, String description, String location, int salary) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", title=" + title + ", description=" + description + ", location=" + location + ", salary=" + salary + '}';
    }
    
    
    
}
