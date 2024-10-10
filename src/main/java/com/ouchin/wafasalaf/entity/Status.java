package com.ouchin.wafasalaf.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "status")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String status;


    @OneToMany(mappedBy = "status")
    private Set<Historic> historics;
}
