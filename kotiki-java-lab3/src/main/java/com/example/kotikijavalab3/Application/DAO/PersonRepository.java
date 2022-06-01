package com.example.kotikijavalab3.Application.DAO;

import com.example.kotikijavalab3.Core.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}