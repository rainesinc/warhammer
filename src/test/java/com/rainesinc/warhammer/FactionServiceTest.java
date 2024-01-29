package com.rainesinc.warhammer;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.repository.FactionRepository;
import com.rainesinc.warhammer.service.FactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FactionServiceTest {
    @Autowired
    private FactionRepository factionRepository;

    private FactionService factionService;

    @BeforeEach
    public void setup(){
        factionService = new FactionService(factionRepository);
    }

    @Test
    public void shouldFindAllFactions(){
        Faction faction = new Faction();
    }
}
