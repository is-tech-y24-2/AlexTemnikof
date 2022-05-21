package com.example.kotikijavalab4.Application.DAO;

import com.example.kotikijavalab4.Core.RelationEntities.CatToCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatToCatRepository extends JpaRepository<CatToCat, Integer> {
    List<CatToCat> findCatToCatsByFirstId(int firstId);
}