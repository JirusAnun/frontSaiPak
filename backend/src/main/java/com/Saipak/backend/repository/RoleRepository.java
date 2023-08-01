package com.Saipak.backend.repository;

import com.Saipak.backend.entity.Roles;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Roles, String> {

    Optional<Roles> findByName(String name);

    boolean existsByName(String name);

}
