package be.perzival.dev.bowlingalike.service;

import be.perzival.dev.bowlingalike.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;

    public void createGame() {
        this.game = new Game();
    }

    public Game getGame() {
        return game;
    }
}
