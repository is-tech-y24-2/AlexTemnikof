package Dao.Dao;

import Dao.Entities.Cat;
import Dao.Entities.Person;
import Dao.RelationClasses.CatToCat;
import Dao.RelationClasses.PersonToCat;

import java.util.List;

public interface PersonToCatDao {
    PersonToCat findById(int id);

    void save(PersonToCat personToCat);

    void update(PersonToCat personToCat);

    void delete(PersonToCat personToCat);

    List<Cat> findCatsOfThePerson(Person person);

    List<PersonToCat> findAll();
}
