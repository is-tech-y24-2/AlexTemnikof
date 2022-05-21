package com.example.kotikijavalab3.Application.DAO;

import com.example.kotikijavalab3.Core.RelationEntities.PersonToCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonToCatRepository extends JpaRepository<PersonToCat, Integer> {
    List<PersonToCat> findPersonToCatsByPersonId(int personId);
}