package com.example.kotikijavalab3.Core.Entities;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "personid")
    int personId;
    @Column(name = "name")
    String name;
    @Column(name = "birthday")
    String birthday;

    public Person(){

    }

    public Person (String name, String birthday){
        this.name = name;
        this.birthday = birthday;
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

    @Override
    public String toString(){
        return "This is the person with " + personId + " who's name "
                + name + " and who's birthday " + birthday;
    }

}