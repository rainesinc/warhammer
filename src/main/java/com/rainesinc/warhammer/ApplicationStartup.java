package com.rainesinc.warhammer;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.service.FactionService;
import com.rainesinc.warhammer.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private FactionService factionService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        Faction aFaction = new Faction();
        aFaction.setName("BRETONNIA");
        factionService.addFaction(aFaction);
        Faction bFaction = new Faction();
        bFaction.setName("TOMB KINGS");
        factionService.addFaction(bFaction);

        Role aRole = new Role();
        aRole.setName("ROLE_ADMIN");
        roleRepository.save(aRole);
        Role bRole = new Role();
        bRole.setName("ROLE_USER");
        roleRepository.save(bRole);



        try {
            var adminRoleList =
                    StreamSupport
                            .stream(roleRepository.findAll().spliterator(),false).toList();

            List<Role> userRoleList = new ArrayList<>();
            userRoleList.add(aRole);

            User adminUser1 = new User();
            adminUser1.setEmail("admin@rainesinc.com");
            adminUser1.setPassword("password");
            adminUser1.setRoles(adminRoleList);
            userService.createUser(adminUser1);

            User user1 = new User();
            user1.setEmail("user1@rainesinc.com");
            user1.setPassword("password");
            user1.setRoles(userRoleList);
            userService.createUser(user1);

            User user2 = new User();
            user2.setEmail("user2@rainesinc.com");
            user2.setPassword("password");
            user2.setRoles(userRoleList);
            userService.createUser(user2);


        } catch (NoSuchAlgorithmException | BadRequestException e) {
            throw new RuntimeException(e);
        }


    }

}