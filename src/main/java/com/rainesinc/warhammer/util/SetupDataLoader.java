package com.rainesinc.warhammer.util;

import com.rainesinc.warhammer.entity.Privilege;
import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.repository.PrivilegeRepository;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.service.UserService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(value = "com.rainesinc.warhammer")
public class SetupDataLoader {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PrivilegeRepository privilegeRepository;
//
//    @PersistenceContext
//    @Autowired
//    private EntityManager entityManager;
//
//    public static void main(String[] args) {
//        SpringApplication.run(SetupDataLoader.class);
//    }
//    @Bean
//    public CommandLineRunner loader() {
//        return (args) -> {
//            Privilege readPrivilege
//                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
//            Privilege writePrivilege
//                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//            List<Privilege> adminPrivileges = Arrays.asList(
//                    readPrivilege, writePrivilege);
//            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//            createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//
//            Role adminRole = roleRepository.findByName("ROLE_USER");
//
//            userService.createUser("test5@test.com", "password");
//        };
//    }
//
//    @Transactional
//    private Privilege createPrivilegeIfNotFound(String name) {
//
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    private void createRoleIfNotFound(
//            String name, Collection<Privilege> privileges) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//    }
}