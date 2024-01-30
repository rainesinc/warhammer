package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.repository.MiniatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MiniatureServiceMockTest {
    @Mock
    MiniatureRepository miniatureRepository;

    @InjectMocks
    private MiniatureService miniatureService;

    @Test
    public void canAddMiniature(){
        // given
        Miniature miniature = new Miniature();
        miniature.setName("Clan Rat");

        // when
        miniatureService.addMiniature(miniature);

        // then
        ArgumentCaptor<Miniature> factionArgumentCaptor = ArgumentCaptor.forClass(
                Miniature.class
        );
        verify(miniatureRepository).save(factionArgumentCaptor.capture());
        Miniature capturedMiniature = factionArgumentCaptor.getValue();

        assertThat(capturedMiniature).isEqualTo(miniature);
    }
}
