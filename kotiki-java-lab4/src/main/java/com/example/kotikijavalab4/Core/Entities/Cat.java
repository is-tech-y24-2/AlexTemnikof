package com.example.kotikijavalab4.Core.Entities;

import com.example.kotikijavalab4.Core.Enums.Breed;
import com.example.kotikijavalab4.Core.Enums.Color;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "cats")
public class Cat {
    @Id
    @GeneratedValue
    @Column (name = "catid")
    private int catId;
    @Column (name = "name")
    private String name;
    @Column ( name = "breed")
    private int breed;
    @Column (name = "color")
    private int color;

    public Cat(){

    }

    public Cat(String name, int breed, int color){
        this.name = name;
        this.breed = breed;
        this.color = color;
    }

    public int getCatId(){
        return catId;
    }

    public String getName(){
        return name;
    }

    public int getBreed(){
        return breed;
    }

    public int getColor(){
        return color;
    }


    @Override
    public String toString(){
        return "This is the " + Color.values()[color] + " " + Breed.values()[breed] + " cat with " + catId
                + " id" + " which name is " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return catId == cat.catId && breed == cat.breed && color == cat.color && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, name, breed, color);
    }
}