package be.perzival.dev.bowlingalike.controller;

import be.perzival.dev.bowlingalike.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerRestController {
    private final PlayerService playerService;


    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("/score/{pinsDown}")
    public ResponseEntity<Object> playTurn(@PathVariable("pinsDown") int pinsDown) {
        playerService.playTurn(pinsDown);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/score/")
    public ResponseEntity<Object> getScoreBoard() {
        return ResponseEntity.ok().body(playerService.getScore());
    }


}
