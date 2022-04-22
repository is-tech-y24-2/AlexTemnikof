package Dao.Entities;

import Dao.Enums.Breed;
import Dao.Enums.Color;
import javax.persistence.*;
import java.util.*;

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
    //private Breed breed;
    //private Color color;
    // список котиков с которыми дружит

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
        return "This is the" + Color.values()[color] + " " + Breed.values()[breed] + " cat with " + catId
                + " id" + " which name is " + name;
    }

}
