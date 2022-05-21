package com.example.kotikijavalab3.Service.Services;

import com.example.kotikijavalab3.DTO.*;
import com.example.kotikijavalab3.DTO.DTOTransferService;
import com.example.kotikijavalab3.Service.IServices.IUserService;
import com.example.kotikijavalab3.Core.Entities.Cat;
import com.example.kotikijavalab3.Core.Entities.Person;
import com.example.kotikijavalab3.Core.RelationEntities.CatToCat;
import com.example.kotikijavalab3.Core.RelationEntities.PersonToCat;
import com.example.kotikijavalab3.Application.DAO.CatRepository;
import com.example.kotikijavalab3.Application.DAO.CatToCatRepository;
import com.example.kotikijavalab3.Application.DAO.PersonRepository;
import com.example.kotikijavalab3.Application.DAO.PersonToCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configurable
public class UserService implements IUserService {
    public Cat createCat(String name, int breed, int color){
        return new Cat(name, breed, color);
    }

    public Person createPerson(String name, String birthday){
        return new Person(name, birthday);
    }

    public PersonToCat createPersonToCat(int ownerId, int catId){
        return new PersonToCat(ownerId, catId);
    }

    public CatToCat createCatToCat(int firstId, int secondId){
        return new CatToCat(firstId, secondId);
    }
}
