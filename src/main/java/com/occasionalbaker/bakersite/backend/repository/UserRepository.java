package com.occasionalbaker.bakersite.backend.repository;

import com.occasionalbaker.bakersite.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT user FROM User user Where user.id = ?1")
    User findOne(Long id);
}
