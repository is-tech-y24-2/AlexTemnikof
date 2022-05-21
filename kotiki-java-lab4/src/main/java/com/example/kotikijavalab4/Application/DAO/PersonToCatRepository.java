package com.example.kotikijavalab4.Application.DAO;

import com.example.kotikijavalab4.Core.RelationEntities.PersonToCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonToCatRepository extends JpaRepository<PersonToCat, Integer> {
    List<PersonToCat> findPersonToCatsByPersonId(int personId);
}