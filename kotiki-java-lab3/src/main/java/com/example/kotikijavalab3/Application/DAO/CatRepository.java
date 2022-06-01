package com.example.kotikijavalab3.Application.DAO;

import com.example.kotikijavalab3.Core.Entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}