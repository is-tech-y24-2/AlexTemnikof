package com.example.kotikijavalab4.Config;

import com.example.kotikijavalab4.Service.Services.UserSecurityService;
import com.example.kotikijavalab4.Service.Tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSecurityService userSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").authenticated()
                .antMatchers("/authorized/**").authenticated()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable()
                .formLogin();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new Converter().passwordEncoder());
        authenticationProvider.setUserDetailsService(userSecurityService);
        return authenticationProvider;
    }
}
