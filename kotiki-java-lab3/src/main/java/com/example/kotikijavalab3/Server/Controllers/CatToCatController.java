package com.example.kotikijavalab3.Server.Controllers;

import com.example.kotikijavalab3.Application.Services.AppService;
import com.example.kotikijavalab3.Core.RelationEntities.CatToCat;
import com.example.kotikijavalab3.DTO.CatDTO;
import com.example.kotikijavalab3.DTO.CatToCatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cattocat")
public class CatToCatController {
    @Autowired
    AppService service;

    @PostMapping("/regnewfriendship")
    @ResponseBody
    public CatToCatDTO regNewFriendship(Integer firstId, Integer secondId){
        return service.registerTheFriendship(firstId, secondId);
    }

    @GetMapping("/getfriendshipbyid")
    @ResponseBody
    public CatToCatDTO getFriendshipById(Integer id){
        return service.findFriendshipById(id);
    }

    @GetMapping("/getallfriendships")
    @ResponseBody
    public List<CatToCat> getAllFriendships(){
        return service.getAllFriendships();
    }

    @GetMapping("/getfriendsofthecat")
    @ResponseBody
    public List<CatDTO> getFriendsOfTheCat(Integer id){
        return service.getAllFriendsOfTheCatById(id);
    }

    @DeleteMapping("/deletefriendshipbyid")
    @ResponseBody
    public String deleteFriendshipById(Integer id){
        service.deleteTheFriendShipById(id);
        return "done!";
    }
}
