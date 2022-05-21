package com.example.kotikijavalab4.Core.RelationEntities;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatToCat catToCat = (CatToCat) o;
        return friendshipId == catToCat.friendshipId && firstId == catToCat.firstId && secondId == catToCat.secondId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendshipId, firstId, secondId);
    }
}
