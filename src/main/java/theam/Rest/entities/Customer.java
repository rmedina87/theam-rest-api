/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author equipo
 */
@Entity
@Table(name="Customers")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String photo;
    private Long lastUserUpdated = new Long(-1);

    public Customer() {
    }

    public Customer(Long id, String name, String surname, String photo, Long lastUserUpdated) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.lastUserUpdated = lastUserUpdated;
    }

    public Long getLastUserUpdated() {
        return lastUserUpdated;
    }

    public void setLastUserUpdated(Long lastUserUpdated) {
        this.lastUserUpdated = lastUserUpdated;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

 
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", photo=" + photo + ", lastUserUpdated=" + lastUserUpdated + '}';
    }
    

 
    
}
