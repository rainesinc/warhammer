package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(value = "com.rainesinc.warhammer")
public class MiniatureServiceTest {

    @Autowired
    private MiniatureService miniatureService;
    @Autowired
    private FactionService factionService;

    @Test
    public void shouldFindAllMiniatures(){
        Faction faction = new Faction();
        faction.setName("HIGH ELVES");
        factionService.addFaction(faction);

        Miniature miniature = new Miniature();
        miniature.setName("Lorthern Sea Gaurd");
        miniature.setFaction(faction);

        miniatureService.addMiniature(miniature);

        Iterable<Miniature> miniatureIterable =
                miniatureService.findAllMiniatures();
        Miniature savedMiniature = miniatureIterable.iterator().next();

        assertThat(savedMiniature).isNotNull();
    }
}
