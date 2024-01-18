package game.handler.animation;

import city.cs.engine.BodyImage;
import game.entity.enemy.SimpleEnemy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class AnimationHandler implements ActionListener {
    private final String timerId;
    private final Animatable entity;
    private final BodyImage[] frames;
    private boolean playOnce;
    private int currentFrame = 0;

    public AnimationHandler(String timerId, Animatable entity, BodyImage[] frames) {
        this.timerId = timerId;
        this.entity = entity;
        this.frames = frames;
    }

    public AnimationHandler(String timerId, Animatable entity, BodyImage[] frames, boolean playOnce) {
        this(timerId, entity, frames);
        this.playOnce = playOnce;
    }

    /**
     * Changes the frames of an animation.
     * <p>
     * This implementation manipulates the currentFrame accordingly to the length of frames, and changes the image of the entity using its method coming from Animatable interface.
     *
     * @param  e the action event
     * @return void at all times
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentFrame >= frames.length) {
            if (this.playOnce) {
                currentFrame = 0;
                entity.changeImage(frames[currentFrame]);
                entity.stopTimer(this.timerId);
                return;
            }

            currentFrame = 0;
        }

        entity.changeImage(frames[currentFrame]);
        currentFrame++;
    }
}
