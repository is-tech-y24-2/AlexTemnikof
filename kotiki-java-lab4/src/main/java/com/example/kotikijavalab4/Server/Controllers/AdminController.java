package com.example.kotikijavalab4.Server.Controllers;

import com.example.kotikijavalab4.Application.Services.AppService;
import com.example.kotikijavalab4.DTO.CatDTO;
import com.example.kotikijavalab4.DTO.CatToCatDTO;
import com.example.kotikijavalab4.DTO.PersonDTO;
import com.example.kotikijavalab4.DTO.PersonToCatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
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
    public List<CatDTO> getAllCats(){
        return appService.getAllCats();
    }

    @DeleteMapping("/deletecatbyid")
    @ResponseBody
    public String deleteCatById(Integer id){
        appService.deleteTheCatById(id);
        return "done!";
    }

    @PostMapping("/regperson")
    @ResponseBody
    public PersonDTO regPerson(String name, String birthday, String username, String password, String role){
        return appService.registerNewPerson(name, birthday, username, password, role);
    }

    @GetMapping("/getpersonbyid")
    @ResponseBody
    public PersonDTO getPersonById(Integer id){
        return appService.findPersonById(id);
    }

    @GetMapping("/getallpersons")
    @ResponseBody
    public List<PersonDTO> getAllPersons(){
        return appService.getAllPersons();
    }

    @DeleteMapping("/deletepersonbyid")
    @ResponseBody
    public String deletePersonById(Integer id){
        appService.deletePersonById(id);
        return "done!";
    }

    @PostMapping("/regnewfriendship")
    @ResponseBody
    public CatToCatDTO regNewFriendship(Integer firstId, Integer secondId){
        return appService.registerTheFriendship(firstId, secondId);
    }

    @GetMapping("/getfriendshipbyid")
    @ResponseBody
    public CatToCatDTO getFriendshipById(Integer id){
        return appService.findFriendshipById(id);
    }

    @GetMapping("/getallfriendships")
    @ResponseBody
    public List<CatToCatDTO> getAllFriendships(){
        return appService.getAllFriendships();
    }

    @GetMapping("/getfriendsofthecat")
    @ResponseBody
    public List<CatDTO> getFriendsOfTheCat(Integer id){
        return appService.getAllFriendsOfTheCatById(id);
    }

    @DeleteMapping("/deletefriendshipbyid")
    @ResponseBody
    public String deleteFriendshipById(Integer id){
        appService.deleteTheFriendShipById(id);
        return "done!";
    }

    @PostMapping("/regnewownership")
    @ResponseBody
    public PersonToCatDTO regNewOwnership(Integer personid, Integer catid){
        return appService.registerTheOwnership(personid, catid);
    }

    @GetMapping("/getownershipbyid")
    @ResponseBody
    public PersonToCatDTO getOwnershipById(Integer id){
        return appService.findOwnershipById(id);
    }

    @GetMapping("/getallownerships")
    @ResponseBody
    public List<PersonToCatDTO> getAllOwnerShips(){
        return appService.getAllOwnerships();
    }

    @GetMapping("/getcatsoftheowner")
    @ResponseBody
    public List<CatDTO> getCatsOfTheOwner(Integer id){
        return appService.getAllCatsOfThePersonById(id);
    }

    @DeleteMapping("/deleteownershipbyid")
    @ResponseBody
    public String deleteOwnershipById(Integer id){
        appService.deleteTheOwnerShipById(id);
        return "done!";
    }
}
