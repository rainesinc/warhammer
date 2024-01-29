package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.repository.FactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(value = "com.rainesinc.warhammer")
public class FactionServiceTest {

    @Autowired
    private FactionService factionService;

    @Test
    public void shouldFindAllFactions(){
        Faction faction = new Faction();
        faction.setName("HIGH ELVES");

        factionService.addFaction(faction);
        Iterable<Faction> factionList = factionService.findAllFactions();
        Faction savedFaction = factionList.iterator().next();

        assertThat(savedFaction).isNotNull();

        // cleanup
        factionService.removeFactionById(savedFaction.getId());
    }

}
