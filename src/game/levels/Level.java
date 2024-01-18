package game.levels;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.Game;
import game.entity.Player;
import game.entity.enemy.Enemy;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public abstract class Level extends World {
    public static final int DEFAULT_DELAY_ANIMATION = 50;
    public static final float DEFAULT_TILE_SCALE = 3f;
    public static final float DEFAULT_SUBTILE_SCALE = 1.7f;
    public static final BodyImage BROWN_PLATFORM = new BodyImage("data/images/platform/brown.png");

    public static SoundClip BACKGROUND;

    private Game game;
    private Player player;
    public Image background;

    public Level(Game game, Image background) {
        this.game = game;
        this.background = background;
        this.player = new Player(this);
    }

    static {
        try {
            BACKGROUND = new SoundClip("data/audio/background.wav");
            BACKGROUND.setVolume(2);
            BACKGROUND.loop();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Abstract method with specific implementation.
     * <p>
     * This abstract method is able to take any implementation coming from classes that are extended by Level. This will check whether a level has been completed by the player or not.
     *
     * @return boolean to determine true (completed) or false (not completed)
     */
    public boolean isComplete() {
        boolean done = true;

        for (DynamicBody body : this.getDynamicBodies()) {
            if (body instanceof Enemy) {
                done = false;
            }
        }

        return done;
    }

    public abstract String getLevelName();

    public abstract void populate();

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
