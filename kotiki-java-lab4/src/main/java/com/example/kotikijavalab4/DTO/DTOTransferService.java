package com.example.kotikijavalab4.DTO;

import com.example.kotikijavalab4.Core.Entities.*;
import com.example.kotikijavalab4.Core.RelationEntities.*;

public class DTOTransferService {

    public static CatDTO catDTOTransfer(Cat object){
        CatDTO catDto = new CatDTO();
        catDto.setId(object.getCatId());
        catDto.setName(object.getName());
        catDto.setBreed(object.getBreed());
        catDto.setColor(object.getColor());
        return catDto;
    }

    public static PersonDTO personDTOTransfer(Person object){
        PersonDTO personDto = new PersonDTO();
        personDto.setId(object.getPersonId());
        personDto.setName(object.getName());
        personDto.setBirthday(object.getBirthday());
        return personDto;
    }

    public static CatToCatDTO catToCatDTOTransfer(CatToCat object){
        CatToCatDTO catToCatDTO = new CatToCatDTO();
        catToCatDTO.setFriendshipId(object.getFriendshipId());
        catToCatDTO.setFirstId(object.getFirstId());
        catToCatDTO.setSecondId(object.getSecondId());
        return catToCatDTO;
    }

    public static PersonToCatDTO personToCatDTOTransfer(PersonToCat object){
        PersonToCatDTO personToCatDTO = new PersonToCatDTO();
        personToCatDTO.setOwnershipId(object.getOwnershipId());
        personToCatDTO.setOwnerId(object.getPersonId());
        personToCatDTO.setCatId(object.getCatId());
        return personToCatDTO;
    }
}
