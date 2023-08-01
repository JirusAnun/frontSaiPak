package com.Saipak.backend.repository;

import com.Saipak.backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findById(String userId);
    boolean existsByUsername(String username);


}
