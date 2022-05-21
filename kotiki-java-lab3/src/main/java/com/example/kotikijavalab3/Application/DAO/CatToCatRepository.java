package com.example.kotikijavalab3.Application.DAO;

import com.example.kotikijavalab3.Core.RelationEntities.CatToCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CatToCatRepository extends JpaRepository<CatToCat, Integer> {
    List<CatToCat> findCatToCatsByFirstId(int firstId);
}