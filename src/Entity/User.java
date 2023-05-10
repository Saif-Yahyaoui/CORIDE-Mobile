/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author hanin
 */
public class User {
    private int id;
    private String name, last_name,email,password,gender,phone;
   // private Date birthday;

    public User() {
    }

    public User(String name, String last_name, String email, String password, String gender, String phone) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
    }

    public User(int id, String name, String last_name, String email, String password, String gender, String phone) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", last_name=" + last_name + ", email=" + email + ", password=" + password + ", gender=" + gender + ", phone=" + phone + '}';
    }
    
    
}