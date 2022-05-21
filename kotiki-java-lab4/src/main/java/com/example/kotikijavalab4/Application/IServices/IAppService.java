package com.example.kotikijavalab4.Application.IServices;

import com.example.kotikijavalab4.Core.Entities.Person;
import com.example.kotikijavalab4.DTO.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppService {
    CatDTO registerCat(String name, int breed, int color);
    PersonDTO registerNewPerson(String name, String birthday, String username, String password, String role);
    PersonToCatDTO registerTheOwnership(int ownerId, int catId);
    CatToCatDTO registerTheFriendship(int firstCatId, int secondCatId);
    void deleteTheOwnerShipById(int ownershipId);
    void deleteTheFriendShipById(int friendShipId);
    void deleteTheCatById(int catId);
    void deletePersonById(int personId);
    CatDTO findCatById(int id);
    PersonDTO findPersonById(int id);
    CatToCatDTO findFriendshipById(int id);
    PersonToCatDTO findOwnershipById(int id);
    List<CatDTO> getAllCatsOfThePersonById(int id);
    List<CatDTO> getAllFriendsOfTheCatById(int id);
    List<PersonToCatDTO> getAllOwnerships();
    List<CatToCatDTO> getAllFriendships();
    List<CatDTO> getAllCats();
    List<PersonDTO> getAllPersons();
    Person findPersonByUsername(String username);
}
