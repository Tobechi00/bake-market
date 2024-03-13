package com.occasionalbaker.bakersite.backend.controller;

import com.occasionalbaker.bakersite.backend.entity.Role;
import com.occasionalbaker.bakersite.backend.entity.User;
import com.occasionalbaker.bakersite.backend.repository.UserRepository;
import com.occasionalbaker.bakersite.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String firstname, String lastname, String phonenumber, String email, String password) {

            User user = new User(firstname, lastname, phonenumber, email, passwordEncoder.encode(password));
            user.setRole(Role.USER);

            userService.saveUser(user);

        }
        public Boolean userExists(String username){
        User user = userRepository.findByUsername(username);

            if (user == null){
                return false;
            }else {
                return true;
            }
        }
    }

