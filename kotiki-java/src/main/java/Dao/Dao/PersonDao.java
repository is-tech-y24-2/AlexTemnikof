package Dao.Dao;

import Dao.Entities.Cat;
import Dao.Entities.Person;

import java.util.List;

public interface PersonDao {

    Person findById(int id);

    void save(Person person);

    void update(Person person);

    void delete(Person person);

    List<Person> findAll();
}
