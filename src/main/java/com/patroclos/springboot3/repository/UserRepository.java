package com.patroclos.springboot3.repository;


import com.patroclos.springboot3.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}