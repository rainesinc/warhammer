package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public Iterable<Role> findAllRoles() {
        return repo.findAll();
    }

    public void removeRoleById(int id){
        repo.deleteById(id);
    }

    public Role addRole(Role role){
        return repo.save(role);
    }

    public void updateRole(Role role){
        repo.save(role);
    }

    public Role findRoleById(final int id) throws NotFoundException {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Role with id = " + id + " was not found.")
                );
    }
}
