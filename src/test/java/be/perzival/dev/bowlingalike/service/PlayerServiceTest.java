package be.perzival.dev.bowlingalike.service;


import be.perzival.dev.bowlingalike.model.Frame;
import be.perzival.dev.bowlingalike.model.Game;
import be.perzival.dev.bowlingalike.model.Throw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;


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

        List<Frame> frameList = frameService.getFrameList();
        Assertions.assertThat(frameList).hasSize(5);
        for (Frame frame : frameList) {
            Assertions.assertThat(frame.getThrowList()).hasSize(3);
            Assertions.assertThat(frame.getThrowList().get(0)).isEqualTo(4);
            Assertions.assertThat(frame.getThrowList().get(1)).isEqualTo(3);
            Assertions.assertThat(frame.getThrowList().get(2)).isEqualTo(7);
        }
        Assertions.assertThat(playerService.getFinalScore()).isEqualTo(70);
    }

    @Test
    void FullGameWithStrikeAndSpare_1() {
        Integer numberofPins[] = new Integer[]
                {
                        15,
                        8, 1, 2,
                        1, 2, 12,
                        6, 4, 1,
                        15, 8, 2, 3,
                };
        for (int i = 0; i < numberofPins.length; i++) {
            playerService.playTurn(numberofPins[i]);
        }

        List<Frame> frameList = frameService.getFrameList();
        Assertions.assertThat(frameList).hasSize(5);
        Assertions.assertThat(playerService.getFinalScore()).isEqualTo(101);
    }

    @Test
    void FullGameWithStrikeAndSpare_2() {
        Integer numberofPins[] = new Integer[]
                {
                        8, 1, 1,
                        8, 7,
                        1, 2, 1,
                        15,
                        1, 2, 1,
                };
        for (int i = 0; i < numberofPins.length; i++) {
            playerService.playTurn(numberofPins[i]);
        }

        List<Frame> frameList = frameService.getFrameList();
        Assertions.assertThat(frameList).hasSize(5);
        Assertions.assertThat(playerService.getFinalScore()).isEqualTo(55);
    }
}
