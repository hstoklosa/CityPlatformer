package game.entity.pickup;

import city.cs.engine.*;
import game.entity.Player;
import game.handler.animation.Animatable;
import game.handler.animation.AnimationHandler;
import game.levels.Level;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.HashMap;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public abstract class Pickup extends StaticBody implements Animatable {
    private AttachedImage attachedImg;
    private Timer idleTimer;

    public HashMap<String, Timer> timers = new HashMap<>();

    public Pickup(Level level, Vec2 position, Shape pickupShape) {
        super(level, pickupShape);
        this.setPosition(position);
    }

    public Pickup(Level level, Vec2 position, Shape pickupShape, BodyImage[] frames) {
        this(level, pickupShape, position, frames, Level.DEFAULT_DELAY_ANIMATION);
    }

    public Pickup(Level level, Shape pickupShape, Vec2 position, BodyImage[] frames, int delay) {
        this(level, position, pickupShape);
        this.addImage(frames[0]);

        AnimationHandler idle = new AnimationHandler("idle", this, frames);
        idleTimer = new Timer(200, idle);
        idleTimer.start();
    }

    @Override
    public void changeImage(BodyImage newImage) {
        /**
         *         Random ran = new Random();
         *         List<Fixture> fixtureList = this.getFixtureList();
         *
         *         if (fixtureList.size() > 0) {
         *             this.getFixtureList().get(0).destroy();
         *         }
         *         Fixture sf = new SolidFixture(this, new BoxShape(ran.nextFloat(3-1) + 1, ran.nextFloat(6-2) + 2));
         *         SolidFixture sf = new SolidFixture(this, new BoxShape(1, 2));
         *         sf.setRestitution(0.3f);
         */

        this.removeAttachedImage(this.attachedImg);
        this.removeAllImages();
        this.attachedImg = this.addImage(newImage);
    }

    public abstract void applyPickup(CollisionEvent e);
}
