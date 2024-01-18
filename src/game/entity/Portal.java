package game.entity;

import city.cs.engine.*;
import game.handler.animation.Animatable;
import game.handler.animation.AnimationHandler;
import game.levels.Level;
import game.utils.SpriteLoader;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class Portal extends StaticBody implements Animatable {
    private static final Shape PORTAL_SHAPE = new BoxShape(1, 2);
    private static final BodyImage[] IDLE_FRAMES = SpriteLoader.loadSprites("data/images/portal/purple/", 5);
    private AttachedImage attachedImg;
    private Timer idleTimer;

    public Portal(Level level, Vec2 position) {
        super(level, PORTAL_SHAPE);

        this.setPosition(position);

        idleTimer = new Timer(125, new AnimationHandler("idle", this, IDLE_FRAMES));
        idleTimer.start();

        Sensor sensor = new Sensor(this, PORTAL_SHAPE);
        sensor.addSensorListener(new SensorListener() {
            final Player p = level.getPlayer();

            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Player && level.isComplete()) {
                    try {
                        level.getGame().nextLevel();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });

    }

    @Override
    public void changeImage(BodyImage newImage) {
        this.removeAllImages();
        this.removeAttachedImage(this.attachedImg);
        this.attachedImg = this.addImage(newImage);
    }

    @Override
    public void stopTimer(String timerId) {}

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}
}
