package game.entity.enemy;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import game.entity.projectile.Projectile;
import game.handler.animation.AnimationHandler;
import game.levels.Level;
import game.utils.SpriteLoader;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class ShootingEnemy extends Enemy {
    private static final Shape ENEMY_SHAPE = new BoxShape(1, 1.25f);
    private final Timer shootTimer;

    public ShootingEnemy(Level level, Vec2 position) {
        super(level, position, ENEMY_SHAPE);

        // shoot towards the position of the player
        shootTimer = new Timer(2000, e -> {
            Vec2 towards = ((Level) super.getWorld()).getPlayer().getPosition();
            shootProjectile(
                    towards,
                    new Projectile((Level) this.getWorld(), this, Projectile.Type.RANGE)
            );
        });

        shootTimer.start();
    }

    @Override
    public void destroy() {
        shootTimer.stop();
        super.destroy();
    }

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {
        final BodyImage[] RUN_FRAMES = SpriteLoader.loadSprites("data/images/enemy/slime/idle/", 2.5f);
        final BodyImage[] HIT_FRAMES = SpriteLoader.loadSprites("data/images/enemy/slime/hit/");

        AnimationHandler run = new AnimationHandler("run", this, RUN_FRAMES);
        Timer runTimer = new Timer(50, run);
        runTimer.start();
        timers.put("run", runTimer);

        AnimationHandler hit = new AnimationHandler("hit", this, HIT_FRAMES, true);
        Timer hitTimer = new Timer(50, hit);
        timers.put("hit", hitTimer);

        super.setRunTimer(runTimer);
        super.setHitTimer(hitTimer);
    }

    @Override
    public void stopTimer(String timerId) {
        timers.get(timerId).stop();

        switch (timerId) {
            case "hit" -> {
                timers.get("run").start();
            }
            default -> {}
        }
    }
}
