package com.example.kotikijavalab4.Application.DAO;

import com.example.kotikijavalab4.Core.Entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}