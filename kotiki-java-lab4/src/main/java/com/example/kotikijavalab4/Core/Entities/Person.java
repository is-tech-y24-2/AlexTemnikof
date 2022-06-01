package com.example.kotikijavalab4.Core.Entities;

import com.example.kotikijavalab4.Service.Tools.Converter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "personid")
    private int personId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "birthday")
    private String birthday;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private String role;

    public Person(){

    }

    public Person (String name, String birthday, String username, String password, String role){
        this.name = name;
        this.birthday = birthday;
        this.username = username;
        this.password = new Converter().passwordEncoder().encode(password);
        this.role = role;
    }

    public int getPersonId(){
        return personId;
    }

    public String getName(){
        return name;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    @Override
    public String toString(){
        return "This is the person with " + personId + " who's name "
                + name + " and who's birthday " + birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && Objects.equals(username, person.username) && Objects.equals(password, person.password) && Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, birthday, username, password, role);
    }
}
