package game.handler.animation;

import city.cs.engine.BodyImage;

import javax.swing.*;
import java.util.HashMap;


public interface Animatable {
    void changeImage(BodyImage newImage);
    void stopTimer(String timerId);
    void loadTimers(HashMap<String, Timer> timers);
}
