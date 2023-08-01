package com.Saipak.backend.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "m_roles")
public class Roles extends BaseEntity {

    private String name;

    Roles() {

    }

    public Roles(String name) {
        this.name = name;
    }

}
