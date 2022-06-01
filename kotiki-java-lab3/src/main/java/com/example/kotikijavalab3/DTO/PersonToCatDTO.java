package com.example.kotikijavalab3.DTO;

public class PersonToCatDTO {
    private int ownershipId;
    private int ownerId;
    private int catId;

    public void setOwnershipId(int ownershipId) {
        this.ownershipId = ownershipId;
    }

    public int getOwnershipId(){
        return ownershipId;
    }

    public void setOwnerId(int id){
        ownerId = id;
    }

    public int getOwnerId(){
        return ownerId;
    }

    public void setCatId(int id){
        catId = id;
    }

    public int getCatId(){
        return catId;
    }
}
