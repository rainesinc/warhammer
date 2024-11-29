package com.rainesinc.warhammer;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.service.FactionService;
import com.rainesinc.warhammer.service.MiniatureService;
import com.rainesinc.warhammer.service.UserService;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;

import java.security.NoSuchAlgorithmException;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private FactionService factionService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MiniatureService miniatureService;

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        Faction aFaction = new Faction();
        aFaction.setName("BRETONNIA");
        factionService.addFaction(aFaction);
        Faction bFaction = new Faction();
        bFaction.setName("TOMB KINGS");
        factionService.addFaction(bFaction);

        Faction faction = factionService.findFactionByName("BRETONNIA");
        Miniature miniature = new Miniature();
        miniature.setName("Pikemen");
        miniature.setFaction(faction);
        miniatureService.addMiniature(miniature);

        Role aRole = new Role();
        aRole.setName("ROLE_ADMIN");
        roleRepository.save(aRole);
        Role bRole = new Role();
        bRole.setName("ROLE_USER");
        roleRepository.save(bRole);

        Role role = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setEmail("admin@rainesinc.com");
        user.setPassword("password");
        user.getRoles().add(role);
        try {
            userService.createUser(user);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

}