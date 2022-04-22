package Service;

import Dao.Dao.CatDao;
import Dao.Dao.CatToCatDao;
import Dao.Dao.PersonDao;
import Dao.Dao.PersonToCatDao;
import Dao.DaoImplementations.CatDaoImpl;
import Dao.DaoImplementations.CatToCatDaoImpl;
import Dao.DaoImplementations.PersonDaoImpl;
import Dao.DaoImplementations.PersonToCatDaoImpl;
import Dao.Entities.*;
import Dao.RelationClasses.CatToCat;
import Dao.RelationClasses.PersonToCat;

import java.util.*;

public class UserService {

    CatDao catDao = new CatDaoImpl();
    PersonDao personDao = new PersonDaoImpl();
    CatToCatDao catToCatDao = new CatToCatDaoImpl();
    PersonToCatDao personToCatDao = new PersonToCatDaoImpl();

    public Cat registerCat(String name, int breed, int color){
        Cat cat = new Cat(name, breed, color);
        catDao.save(cat);
        return cat;
    }

    public Person registerNewPerson(String name, String birthday){
        Person person = new Person(name, birthday);
        personDao.save(person);
        return person;
    }

    public PersonToCat registerTheOwnership(int ownerId, int catId){
        PersonToCat personToCat = new PersonToCat(ownerId, catId);
        personToCatDao.save(personToCat);
        return personToCat;
    }

    public CatToCat addTheFriendship(int firstCatId, int secondCatId){
        CatToCat catToCat = new CatToCat(firstCatId, secondCatId);
        catToCatDao.save(catToCat);
        return catToCat;
    }

    public void deleteTheOwnerShipById(int ownershipId){
        PersonToCat personToCat = findOwnershipById(ownershipId);
        personToCatDao.delete(personToCat);
    }

    public void deleteTheFriendShipById(int friendShipId){
        CatToCat catToCat = findFriendshipById(friendShipId);
        catToCatDao.delete(catToCat);
    }

    public void deleteTheCatById(int catId){
        Cat cat = findCatById(catId);
        catDao.delete(cat);
    }


    public Cat findCatById(int id){

        return catDao.findById(id);
    }

    public Person findPersonById(int id){

        return personDao.findById(id);
    }

    public CatToCat findFriendshipById(int id){
        CatToCat catToCat = catToCatDao.findById(id);
        return catToCat;
    }

    public PersonToCat findOwnershipById(int id){
        return personToCatDao.findById(id);
    }

    public CatToCat deleteFriendshipById(int id){
        return catToCatDao.findById(id);
    }

    public List<Cat> getAllCatsOfThePersonById(int id){
        Person person = findPersonById(id);
        return personToCatDao.findCatsOfThePerson(person);
    }

    public List<Cat> getAllFriendsOfTheCatById(int id ){
        Cat cat = findCatById(id);
        return catToCatDao.findFriendsOfTheCat(cat);
    }

    public List<PersonToCat> getAllOwnershipsById(){
        return personToCatDao.findAll();
    }

    public List<CatToCat> getAllFriendships(){
        return catToCatDao.findAll();
    }

    public List<Cat> getAllCats(){

        return catDao.findAll();
    }

    public List<Person> getAllPersons(){

        return personDao.findAll();
    }

}
