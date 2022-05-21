package com.example.kotikijavalab3.Server.Controllers;


import com.example.kotikijavalab3.Application.Services.AppService;
import com.example.kotikijavalab3.Core.Entities.Cat;
import com.example.kotikijavalab3.DTO.CatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cat")
public class CatController {
    @Autowired
    AppService appService;

    @PostMapping(value="/regcat")
    @ResponseBody
    public CatDTO registerCat(String name, Integer breed, Integer color){
        return appService.registerCat(name, breed, color);
    }

    @GetMapping(value="/getcatbyid")
    @ResponseBody
    public CatDTO getCatById(Integer id){
        return appService.findCatById(id);
    }

    @GetMapping(value="/getallcats")
    @ResponseBody
    public List<Cat> getAllCats(){
        return appService.getAllCats();
    }

    @DeleteMapping("/deleteCatById")
    @ResponseBody
    public String deleteCatById(Integer id){
        appService.deleteTheCatById(id);
        return "done!";
    }
}
