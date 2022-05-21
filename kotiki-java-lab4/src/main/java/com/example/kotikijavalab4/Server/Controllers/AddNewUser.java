package com.example.kotikijavalab4.Server.Controllers;

import com.example.kotikijavalab4.Application.Services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registration")
public class AddNewUser {
    @Autowired
    AppService appService;

    @PostMapping("/add")
    @ResponseBody
    public void add(String name, String birthday, String username, String password, String role){
        appService.registerNewPerson(name, birthday, username, password, role);
    }
}
