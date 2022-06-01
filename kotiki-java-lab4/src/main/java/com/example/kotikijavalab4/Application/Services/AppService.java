package com.example.kotikijavalab4.Application.Services;

import com.example.kotikijavalab4.Application.DAO.*;
import com.example.kotikijavalab4.Application.IServices.IAppService;
import com.example.kotikijavalab4.Core.Entities.*;
import com.example.kotikijavalab4.Core.RelationEntities.*;
import com.example.kotikijavalab4.DTO.*;
import com.example.kotikijavalab4.Service.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService implements IAppService {
    @Autowired
    UserService service;
    @Autowired
    CatRepository catRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CatToCatRepository catToCatRepository;
    @Autowired
    PersonToCatRepository personToCatRepository;

    public CatDTO registerCat(String name, int breed, int color){
        Cat cat = service.createCat(name, breed, color);
        catRepository.saveAndFlush(cat);
        return DTOTransferService.catDTOTransfer(cat);
    }

    public PersonDTO registerNewPerson(String name, String birthday, String username, String password, String role){
        Person person = service.createPerson(name, birthday, username, password, role);
        personRepository.saveAndFlush(person);
        return DTOTransferService.personDTOTransfer(person);
    }

    public PersonToCatDTO registerTheOwnership(int ownerId, int catId){
        PersonToCat personToCat = service.createPersonToCat(ownerId, catId);
        personToCatRepository.saveAndFlush(personToCat);
        return DTOTransferService.personToCatDTOTransfer(personToCat);
    }

    public CatToCatDTO registerTheFriendship(int firstCatId, int secondCatId){
        CatToCat catToCat = service.createCatToCat(firstCatId, secondCatId);
        catToCatRepository.saveAndFlush(catToCat);
        return DTOTransferService.catToCatDTOTransfer(catToCat);
    }

    public void deleteTheOwnerShipById(int ownershipId){
        personToCatRepository.deleteById(ownershipId);
    }

    public void deleteTheFriendShipById(int friendShipId){
        catToCatRepository.deleteById(friendShipId);
    }

    public void deleteTheCatById(int catId){
        catRepository.deleteById(catId);
    }

    public void deletePersonById(int personId){
        personRepository.deleteById(personId);
    }


    public CatDTO findCatById(int id){

        return DTOTransferService.catDTOTransfer(catRepository.findById(id).get());
    }

    public PersonDTO findPersonById(int id){

        return DTOTransferService.personDTOTransfer(personRepository.findById(id).get());
    }

    public CatToCatDTO findFriendshipById(int id){
        return DTOTransferService.catToCatDTOTransfer(catToCatRepository.findById(id).get());
    }

    public PersonToCatDTO findOwnershipById(int id){
        return DTOTransferService.personToCatDTOTransfer(personToCatRepository.findById(id).get());
    }

    public List<CatDTO> getAllCatsOfThePersonById(int id){
        List<PersonToCat> arr = personToCatRepository.findPersonToCatsByPersonId(id);
        List<CatDTO> tempArr = new ArrayList<>();
        for (PersonToCat personToCat : arr){
            CatDTO catDTO = this.findCatById(personToCat.getCatId());
            if (catDTO != null)
                tempArr.add(catDTO);
        }
        return tempArr;
    }

    public List<CatDTO> getAllFriendsOfTheCatById(int id){
        List<CatToCat> arr = catToCatRepository.findCatToCatsByFirstId(id);
        List<CatDTO> tempArr = new ArrayList<>();
        for (CatToCat catToCat : arr){
            CatDTO catDTO = this.findCatById(catToCat.getFirstId());
            if (catDTO != null)
                tempArr.add(catDTO);
        }
        return tempArr;
    }

    public List<PersonToCatDTO> getAllOwnerships(){
        List<PersonToCat> list = personToCatRepository.findAll();
        List<PersonToCatDTO> tempList = new ArrayList<>();
        for (PersonToCat personToCat : list){
            tempList.add(DTOTransferService.personToCatDTOTransfer(personToCat));
        }
        return tempList;
    }

    public List<CatToCatDTO> getAllFriendships(){
        List<CatToCat> list = catToCatRepository.findAll();
        List<CatToCatDTO> tempList = new ArrayList<>();
        for (CatToCat catToCat : list){
            tempList.add(DTOTransferService.catToCatDTOTransfer(catToCat));
        }
        return tempList;
    }

    public List<CatDTO> getAllCats(){

        List<Cat> list = catRepository.findAll();
        List<CatDTO> tempList = new ArrayList<>();
        for (Cat cat : list){
            tempList.add(DTOTransferService.catDTOTransfer(cat));
        }
        return tempList;
    }

    public List<PersonDTO> getAllPersons(){

        List<Person> list = personRepository.findAll();
        List<PersonDTO> tempList = new ArrayList<>();
        for (Person person : list){
            tempList.add(DTOTransferService.personDTOTransfer(person));
        }
        return tempList;
    }

    public Person findPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }
}
