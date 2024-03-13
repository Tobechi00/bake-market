package com.occasionalbaker.bakersite.views.session;

import com.occasionalbaker.bakersite.backend.entity.CakeCatalogue;
import com.occasionalbaker.bakersite.backend.entity.Order;
import com.occasionalbaker.bakersite.backend.entity.Role;
import com.occasionalbaker.bakersite.backend.entity.User;
import com.occasionalbaker.bakersite.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SessionAttributes {

    UserRepository userRepository;
    User loggedInUser;

    List<CakeCatalogue> cakeCatalogueList;

    public SessionAttributes(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setLoggedInUser(Long userId){
        this.loggedInUser = userRepository.findOne(userId);
    }

    public String getSessionFirstName(){
        return loggedInUser.getFirstName();
    }

    public String getSessionLastName(){
        return loggedInUser.getLastName();
    }
    public String getSessionEmail(){
        return loggedInUser.getUsername();
    }

    public String getSessionPhoneNumber(){
        return loggedInUser.getPhoneNumber();
    }
    public Role getSessionRole(){
        return loggedInUser.getRole();
    }
    public Set<Order> getOrders(){
        return loggedInUser.getOrder();
    }

}
