package be.perzival.dev.bowlingalike.service;


import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.Game;
import be.perzival.dev.bowlingalike.model.Throw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;


@SpringBootTest
class PlayerServiceTest {
    @Autowired
    PlayerService playerService;
    @Autowired
    FrameService frameService;

    @Test
    void FullGameWithoutAnyStrikeNorSpare() {
        Integer numberofPins[] = new Integer[]{4, 3, 7};

        for (int i = 0; i < 15; i++) {
            playerService.playTurn(numberofPins[i % 3]);
        }

        LinkedList<Frame> frameList = frameService.getFrameList();
        Assertions.assertThat(frameList.size()).isEqualTo(5);
        for (Frame frame : frameList) {
            Assertions.assertThat(frame.getThrowList().size()).isEqualTo(3);
            Assertions.assertThat(frame.getThrowList().get(0).getNumberOfPinDown()).isEqualTo(4);
            Assertions.assertThat(frame.getThrowList().get(1).getNumberOfPinDown()).isEqualTo(3);
            Assertions.assertThat(frame.getThrowList().get(2).getNumberOfPinDown()).isEqualTo(7);
        }
    }
}
