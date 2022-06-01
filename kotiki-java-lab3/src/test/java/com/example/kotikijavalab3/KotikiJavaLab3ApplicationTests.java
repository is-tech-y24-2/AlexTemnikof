package com.example.kotikijavalab3;

import com.example.kotikijavalab3.Application.DAO.CatRepository;
import com.example.kotikijavalab3.Application.DAO.CatToCatRepository;
import com.example.kotikijavalab3.Application.DAO.PersonRepository;
import com.example.kotikijavalab3.Application.DAO.PersonToCatRepository;
import com.example.kotikijavalab3.Application.Services.AppService;
import com.example.kotikijavalab3.DTO.CatDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class KotikiJavaLab3ApplicationTests {

    @Autowired
    AppService appService;

    @MockBean
    CatRepository catRepository;
    @MockBean
    CatToCatRepository catToCatRepository;
    @MockBean
    PersonRepository personRepository;
    @MockBean
    PersonToCatRepository personToCatRepository;

    @Test
    public void createdCatTest(){
        CatDTO cat = appService.registerCat("Boris", 1, 1);
        Assertions.assertEquals(cat, appService.findCatById(cat.getId()));
    }

}
