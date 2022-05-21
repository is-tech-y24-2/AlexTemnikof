package com.example.kotikijavalab3.Application.IServices;

import com.example.kotikijavalab3.Core.Entities.Cat;
import com.example.kotikijavalab3.Core.Entities.Person;
import com.example.kotikijavalab3.Core.RelationEntities.CatToCat;
import com.example.kotikijavalab3.Core.RelationEntities.PersonToCat;
import com.example.kotikijavalab3.DTO.CatDTO;
import com.example.kotikijavalab3.DTO.CatToCatDTO;
import com.example.kotikijavalab3.DTO.PersonDTO;
import com.example.kotikijavalab3.DTO.PersonToCatDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppService {
    CatDTO registerCat(String name, int breed, int color);
    PersonDTO registerNewPerson(String name, String birthday);
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
    List<PersonToCat> getAllOwnerships();
    List<CatToCat> getAllFriendships();
    List<Cat> getAllCats();
    List<Person> getAllPersons();
}
