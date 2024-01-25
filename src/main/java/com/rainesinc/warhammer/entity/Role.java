package com.rainesinc.warhammer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NamedNativeQuery(name = "findRoleByName",
        query = "SELECT r.id, r.name FROM Role r WHERE r.name = ?1",
        resultClass = Role.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    public Role(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}