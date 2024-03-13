package com.occasionalbaker.bakersite.backend.service;

import com.occasionalbaker.bakersite.backend.entity.PrincipalUser;
import com.occasionalbaker.bakersite.backend.entity.User;
import com.occasionalbaker.bakersite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService implements UserDetailsService {


    private UserRepository userRepository;


    public User user;


    public PrincipalUser principalUser;


    @Autowired
    public UserService(UserRepository userRepository, User user, PrincipalUser principalUser) {
        this.userRepository = userRepository;
        this.user = user;
        this.principalUser = principalUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException(username);
        }else {
            return new PrincipalUser(user);
        }
    }

    public void saveUser(User userInformation){
        User user = this.userRepository.findByUsername(userInformation.getUsername());

        if (user != null){
            throw new RuntimeException("this user already exists");
        }else {
            this.userRepository.save(userInformation);
        }
    }


}
