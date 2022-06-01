package com.example.kotikijavalab3.Server.Controllers;

import com.example.kotikijavalab3.Application.Services.AppService;
import com.example.kotikijavalab3.DTO.CatDTO;
import com.example.kotikijavalab3.DTO.PersonToCatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persontocat")
public class PersonToCatController {
    @Autowired
    AppService service;

    @PostMapping("/regnewownership")
    @ResponseBody
    public PersonToCatDTO regNewOwnership(Integer personid, Integer catid){
        return service.registerTheOwnership(personid, catid);
    }

    @GetMapping("/getownershipbyid")
    @ResponseBody
    public PersonToCatDTO getOwnershipById(Integer id){
        return service.findOwnershipById(id);
    }

    @GetMapping("/getallownerships")
    @ResponseBody
    public List<PersonToCatDTO> getAllOwnerShips(){
        return service.getAllOwnerships();
    }

    @GetMapping("/getcatsoftheowner")
    @ResponseBody
    public List<CatDTO> getCatsOfTheOwner(Integer id){
        return service.getAllCatsOfThePersonById(id);
    }

    @DeleteMapping("/deleteownershipbyid")
    @ResponseBody
    public String deleteOwnershipById(Integer id){
        service.deleteTheOwnerShipById(id);
        return "done!";
    }
}
