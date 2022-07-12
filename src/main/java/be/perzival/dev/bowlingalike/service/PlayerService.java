package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.model.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final FrameService frameService;

    public PlayerService(FrameService frameService) {
        this.frameService = frameService;
    }

    public void playTurn(int numberOfPin) {
        frameService.addThrow(new Throw(numberOfPin));
    }
}
