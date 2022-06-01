package Dao.RelationClasses;

import javax.persistence.*;

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
}
