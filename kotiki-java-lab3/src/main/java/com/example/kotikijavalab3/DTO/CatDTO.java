package com.example.kotikijavalab3.DTO;

public class CatDTO {
    private int id;
    private String name;
    private int breed;
    private int color;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setBreed(int breed){
        this.breed = breed;
    }

    public int getBreed(){
        return breed;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }
}
