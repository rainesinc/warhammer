package com.rainesinc.warhammer;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.service.FactionService;
import com.rainesinc.warhammer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;


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
//        Faction aFaction = new Faction();
//        aFaction.setName("BRETONNIA");
//        factionService.addFaction(aFaction);
//        Faction bFaction = new Faction();
//        bFaction.setName("TOMB KINGS");
//        factionService.addFaction(bFaction);
//
//        Role aRole = new Role();
//        aRole.setName("ROLE_ADMIN");
//        roleRepository.save(aRole);
//        Role bRole = new Role();
//        bRole.setName("ROLE_USER");
//        roleRepository.save(bRole);
    }

}