package Controller;

import Dao.Entities.Cat;
import Dao.Enums.*;
import Service.UserService;

import java.util.*;

public class CatController {

    UserService service = new UserService();

    public void registerCat(String name, Breed breed, Color color){
        service.registerCat(name, breed.ordinal(), color.ordinal());
    }

    public void deleteCatById(int id){
        service.deleteTheCatById(id);
    }

    public Cat findCatById(int id){
        return service.findCatById(id);
    }

    public List<Cat> getAllCats(){
        return service.getAllCats();
    }

    public List<Cat> getFriendsOfTheCat(int id){
        return service.getAllFriendsOfTheCatById(id);
    }


}
