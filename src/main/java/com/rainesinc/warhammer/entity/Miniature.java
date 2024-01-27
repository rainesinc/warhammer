package com.rainesinc.warhammer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Miniature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private  int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false, updatable = true)
    private Faction faction;

}
