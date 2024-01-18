package game.entity.pickup;

import city.cs.engine.*;
import game.handler.animation.AnimationHandler;
import game.levels.Level;
import game.utils.SpriteLoader;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

public class ChestPickup extends Pickup {
    private static final Shape CHEST_SHAPE = new BoxShape(1f, 1f);
    private static final BodyImage CHEST_IDLE = new BodyImage("data/images/pickup/chest/idle.png", 2.5f);
    private static final BodyImage[] CHEST_FRAMES = SpriteLoader.loadSprites("data/images/pickup/chest/hit", 2.5f);

    private static final String[] COLLECTIBLES = new String[] { "melon", "coin", "chest" };

    private Timer hitTimer;
    private int hits = 3;

    public ChestPickup(Level level, Vec2 position) {
        super(level, position, CHEST_SHAPE);
        super.addImage(CHEST_IDLE);

        AnimationHandler hitHandler = new AnimationHandler("hit", this, CHEST_FRAMES, true);
        hitTimer = new Timer(50, hitHandler);
        timers.put("hit", hitTimer);
    }

    @Override
    public void applyPickup(CollisionEvent e) {
        int idx = new Random().nextInt(COLLECTIBLES.length);
        String collectible = COLLECTIBLES[idx];

        switch (collectible) {
            case "melon" -> new MelonPickup((Level) this.getWorld(), this.getPosition());
            case "coin" -> new CoinPickup((Level) this.getWorld(), this.getPosition());
            case "chest" -> new ChestPickup((Level) this.getWorld(), this.getPosition());
            default -> {}
        }
    }

    @Override
    public void stopTimer(String timerId) {
        this.removeAllImages();

        switch (timerId) {
            case "hit" -> {
                timers.get(timerId).stop();
                this.addImage(CHEST_IDLE);
            }
            default -> {}
        }
    }

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public Timer getHitTimer() {
        return hitTimer;
    }

    public void setHitTimer(Timer hitTimer) {
        this.hitTimer = hitTimer;
    }
}
