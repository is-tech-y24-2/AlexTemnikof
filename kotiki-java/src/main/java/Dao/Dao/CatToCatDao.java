package Dao.Dao;

import Dao.Entities.Cat;
import Dao.RelationClasses.CatToCat;

import java.util.List;

public interface CatToCatDao {
    CatToCat findById(int id);

    void save(CatToCat catToCat);

    void update(CatToCat catToCat);

    void delete(CatToCat catToCat);

    List<Cat> findFriendsOfTheCat(Cat cat);

    List<CatToCat> findAll();
}
