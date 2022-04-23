package Controller;

import Dao.Entities.*;
import Dao.RelationClasses.PersonToCat;
import Service.UserService;

import java.util.List;

public class PersonController {

    UserService service = new UserService();

    public Person registerNewPerson(String name, String birthday){
        return service.registerNewPerson(name, birthday);
    }

    public PersonToCat registerOwnership(int personId, int catId){
        return service.registerTheOwnership(personId, catId);
    }

    public void deleteOwnerShipById(int id){
        service.deleteTheOwnerShipById(id);
    }

    public void findPersonById(int id){
        service.findPersonById(id);
    }

    public void deletePersonById(int id){
        service.deletePersonById(id);
    }

    public List<Person> getAllPersons(){
        return service.getAllPersons();
    }
}
