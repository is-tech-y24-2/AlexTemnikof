package com.example.kotikijavalab3.Server.Controllers;

import com.example.kotikijavalab3.Application.Services.AppService;
import com.example.kotikijavalab3.DTO.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    AppService service;

    @PostMapping("/regperson")
    @ResponseBody
    public PersonDTO regPerson(String name, String birthday){
        return service.registerNewPerson(name, birthday);
    }

    @GetMapping("/getpersonbyid")
    @ResponseBody
    public PersonDTO getPersonById(Integer id){
        return service.findPersonById(id);
    }

    @GetMapping("/getallpersons")
    @ResponseBody
    public List<PersonDTO> getAllPersons(){
        return service.getAllPersons();
    }

    @DeleteMapping("/deletepersonbyid")
    @ResponseBody
    public String deletePersonById(Integer id){
        service.deletePersonById(id);
        return "done!";
    }
}
