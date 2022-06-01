package com.example.kotikijavalab4.Core.RelationEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persontocats")
public class PersonToCat {

    @Id
    @GeneratedValue
    @Column(name = "ownershipid")
    private int ownershipId;
    @Column(name = "personid")
    private int personId;
    @Column (name = "catid")
    private int catId;

    public PersonToCat(){

    }

    public PersonToCat(int personId, int catId){
        this.personId = personId;
        this.catId = catId;
    }

    public int getOwnershipId(){
        return ownershipId;
    }

    public int getPersonId(){
        return personId;
    }

    public int getCatId(){
        return catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonToCat that = (PersonToCat) o;
        return ownershipId == that.ownershipId && personId == that.personId && catId == that.catId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownershipId, personId, catId);
    }
}
