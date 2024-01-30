package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.repository.FactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FactionServiceMockTest {
    @Mock
    FactionRepository factionRepository;

    @InjectMocks
    private FactionService factionService;


    @Test
    public void canAddFaction(){
        // given
        Faction faction = new Faction();
        faction.setName("BRETONNIA");

        // when
        factionService.addFaction(faction);

        // then
        ArgumentCaptor<Faction> factionArgumentCaptor = ArgumentCaptor.forClass(
                Faction.class
        );
        verify(factionRepository).save(factionArgumentCaptor.capture());
        Faction capturedFaction = factionArgumentCaptor.getValue();

        assertThat(capturedFaction).isEqualTo(faction);

    }

}
