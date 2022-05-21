package com.example.kotikijavalab4.Application.DAO;

import com.example.kotikijavalab4.Core.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonByUsername(String username);
}