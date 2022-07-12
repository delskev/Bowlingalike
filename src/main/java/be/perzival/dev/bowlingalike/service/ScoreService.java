package be.perzival.dev.bowlingalike.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScoreService {
    private Map<Integer, Integer> pointMapPerFrame;


    public ScoreService() {
        pointMapPerFrame = new HashMap<>();
    }


    public void addScore(int frame, int score) {
        Integer pointsOfFrame = pointMapPerFrame.get(frame);
        if( pointsOfFrame == null) {
            pointsOfFrame = 0;
        }
        pointsOfFrame += score;

    }
}
