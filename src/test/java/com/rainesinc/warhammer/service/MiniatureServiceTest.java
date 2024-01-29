package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.repository.FactionRepository;
import com.rainesinc.warhammer.repository.MiniatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MiniatureServiceTest {
    @Autowired
    private MiniatureRepository miniatureRepository;

    @Autowired
    private FactionRepository factionRepository;

    private MiniatureService miniatureService;
    private FactionService factionService;

    @BeforeEach
    public void setup(){
        miniatureService = new MiniatureService(miniatureRepository);
        factionService = new FactionService(factionRepository);
    }

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
