package com.example.kotikijavalab3.Core.RelationEntities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cattocat")
public class CatToCat {

    @Id
    @GeneratedValue
    @Column(name = "friendshipid")
    private int friendshipId;
    @Column(name = "firstid")
    private int firstId;
    @Column(name = "secondid")
    private int secondId;

    public CatToCat(){

    }

    public CatToCat(int firstId, int secondId){
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public int getFriendshipId(){ return friendshipId; }

    public int getFirstId(){
        return firstId;
    }

    public int getSecondId(){
        return secondId;
    }
}