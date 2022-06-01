import Dao.Dao.CatDao;
import Dao.DaoImplementations.CatDaoImpl;
import Dao.DaoImplementations.PersonDaoImpl;
import Dao.Entities.Cat;
import Dao.Entities.Person;
import Dao.RelationClasses.PersonToCat;
import Service.UserService;
import net.bytebuddy.description.modifier.Ownership;

import java.util.List;

public class Program {


    public static void main(String[] args) {
        UserService service = new UserService();
        Cat cat = service.registerCat("Kisa", 1, 1);
        Cat cat2 = service.registerCat("Kosak", 1, 1);
        Cat cat3 = service.registerCat("KOSINA", 1 , 1);
        service.addTheFriendship(cat.getCatId(), cat2.getCatId());
        service.addTheFriendship(cat.getCatId(), cat3.getCatId());
        service.addTheFriendship(cat2.getCatId(), cat3.getCatId());
        List<Cat> cats = service.getAllFriendsOfTheCatById(cat.getCatId());
        Person person = service.registerNewPerson("KOKA", "12.03.22");
        service.registerTheOwnership(person.getPersonId(), cat.getCatId());
        List<PersonToCat> ownerships = service.getAllOwnershipsById();
        List<Cat> catsoftheperson = service.getAllCatsOfThePersonById(person.getPersonId());
        List<Cat> sdads = service.getAllCatsOfThePersonById(person.getPersonId());

    }


}
