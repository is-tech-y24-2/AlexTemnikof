package com.example.kotikijavalab4.Server.Controllers;

import com.example.kotikijavalab4.Application.Services.AppService;
import com.example.kotikijavalab4.DTO.CatDTO;
import com.example.kotikijavalab4.DTO.PersonToCatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    AppService appService;

    @PostMapping("/regnewownership")
    @ResponseBody
    public PersonToCatDTO regNewOwnership(Principal principal, Integer catid){
        return appService.registerTheOwnership(appService.findPersonByUsername
                (principal.getName()).getPersonId(), catid);
    }

    @GetMapping("/getcatsoftheowner")
    @ResponseBody
    public List<CatDTO> getCatsOfTheOwner(Principal principal){

        return appService.getAllCatsOfThePersonById(appService.findPersonByUsername(principal.getName()).getPersonId());
    }
}
