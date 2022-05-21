package com.example.kotikijavalab3.Application.DAO;

import com.example.kotikijavalab3.Core.RelationEntities.PersonToCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonToCatRepository extends JpaRepository<PersonToCat, Integer> {
    List<PersonToCat> findPersonToCatsByPersonId(int personId);
}