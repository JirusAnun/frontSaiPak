package com.Saipak.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "hint")
public class Hint {

    @Id
    @Column
    private int id;

    @Column
    private String hint_1;

    @Column
    private String hint_2;

    @Column
    private String hint_3;

    @Column
    private String hint_4;

    @Column
    private String hint_5;
}
