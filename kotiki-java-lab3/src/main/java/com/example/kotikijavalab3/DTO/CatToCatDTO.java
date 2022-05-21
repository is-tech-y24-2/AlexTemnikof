package com.example.kotikijavalab3.DTO;

public class CatToCatDTO {
    private int friendshipId;
    private int firstId;
    private int secondId;

    public void setFriendshipId(int id){
        friendshipId = id;
    }

    public int getFriendshipId(){
        return friendshipId;
    }

    public void setFirstId(int id){
        firstId = id;
    }

    public int getFirstId(int id) {
        return firstId;
    }

    public void setSecondId(int id){
        secondId = id;
    }

    public int getSecondId(int id){
        return secondId;
    }
}
