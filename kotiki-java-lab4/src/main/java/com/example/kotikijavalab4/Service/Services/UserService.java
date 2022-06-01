package com.example.kotikijavalab4.Service.Services;

import com.example.kotikijavalab4.Service.IServices.IUserService;
import com.example.kotikijavalab4.Core.Entities.*;
import com.example.kotikijavalab4.Core.RelationEntities.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configurable
public class UserService implements IUserService {
    public Cat createCat(String name, int breed, int color){
        return new Cat(name, breed, color);
    }

    public Person createPerson(String name, String birthday, String username, String password, String role){
        return new Person(name, birthday, username, password, role);
    }

    public PersonToCat createPersonToCat(int ownerId, int catId){
        return new PersonToCat(ownerId, catId);
    }

    public CatToCat createCatToCat(int firstId, int secondId){
        return new CatToCat(firstId, secondId);
    }
}
