package com.rainesinc.warhammer.util;

import com.rainesinc.warhammer.SpringBootWarhammerApplication;
import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.service.FactionService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableEncryptableProperties
//@ComponentScan(basePackages="com.rainesinc.warhammer")
public class AddFactions implements CommandLineRunner {
    @Autowired
    FactionService factionService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AddFactions.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Faction bretonnia = new Faction();
        bretonnia.setName("BRETONNIA");
        factionService.addFaction(bretonnia);
//        Faction tombKings = new Faction();
//        bretonnia.setName("TOMB KINGS");
//        factionService.addFaction(tombKings);
    }
}
