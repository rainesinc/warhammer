package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(value = "com.rainesinc.warhammer")
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void shouldFindAllRoles(){
        Role role = new Role();
        role.setName("ROLE_TEST");

        roleService.addRole(role);
        Iterable<Role> roleIterable = roleService.findAllRoles();
        Role savedRole = roleIterable.iterator().next();

        assertThat(savedRole).isNotNull();

        // cleanup
        roleService.removeRoleById(savedRole.getId());
    }
}
