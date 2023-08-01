package com.Saipak.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(length = 120)
    private int score = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "m_user_role", joinColumns = @JoinColumn(name = "m_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "m_roles_id", referencedColumnName = "id")
    )
    private Roles role;

    @Column
    private int hint_bought;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hint_id")
    private Hint hint;

}
