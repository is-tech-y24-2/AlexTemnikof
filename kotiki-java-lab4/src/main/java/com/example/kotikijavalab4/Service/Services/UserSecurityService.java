package com.example.kotikijavalab4.Service.Services;

import com.example.kotikijavalab4.Application.Services.AppService;
import com.example.kotikijavalab4.Core.Entities.Person;
import com.example.kotikijavalab4.Service.Tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private AppService appService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = appService.findPersonByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("This person doesn't exist");
        }
        return new Converter().mapUserBDtoUserDetails(user);
    }
}

