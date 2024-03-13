package com.occasionalbaker.bakersite.backend.controller;

import com.occasionalbaker.bakersite.backend.entity.PrincipalUser;
import com.occasionalbaker.bakersite.backend.entity.User;
import com.occasionalbaker.bakersite.backend.repository.UserRepository;
import com.occasionalbaker.bakersite.backend.service.UserService;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;





    public void login(String email, String password){

        User user = userRepository.findByUsername(email);

        if (user == null ||!passwordEncoder.matches(password,user.getPassword()) ){
            throw  new RuntimeException("invalid password or username");
        }
        else {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email,password);
            Authentication authentication = daoAuthenticationProvider.authenticate(authRequest);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);

            System.out.println(VaadinSession.getCurrent());
            System.out.println(sc.getAuthentication().getName());
        }
    }
    public PrincipalUser principalUser(){
        return (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
