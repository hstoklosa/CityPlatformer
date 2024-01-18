package game.entity;

import city.cs.engine.*;
import game.entity.projectile.Projectile;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * Class that extends the Physics Engine's Walker class
 *
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public abstract class Character extends Walker {
    private Timer closeRangeTimer;
    private boolean canCloseRange = true;

    public Character(World world) {
        super(world);
    }

    public Character(World world, Shape shape) {
        super(world, shape);

        closeRangeTimer = new Timer(5000, e -> {
            canCloseRange = true;
            closeRangeTimer.stop();
        });
    }

    /**
     * Creates and moves a projectile from a body.
     * <p>
     * This implementation creates a new Projectile object, and calculates the direction & speed in which the projectile should go based on the towards parameter. There is an offset for the projectile so the body doesn't block it, then the direction & speed is applied to it.
     *
     * @param  towards the (x, y) coordinates from a user mouse click
     * @param  projectile the Projectile object with appropriate attributes
     */
    public void shootProjectile(Vec2 towards, Projectile projectile) {
        Vec2 playerPos = this.getPosition();

        Vec2 direction = towards.sub(playerPos);
        direction.normalize();

        Vec2 velocity = direction.mul(10f);
        projectile.setLinearVelocity(velocity);

        Vec2 offset = direction.mul(2f);
        Vec2 projectilePos = playerPos.add(offset);
        projectile.setPosition(projectilePos);
    }

    // Getters & Setters
    public Timer getCloseRangeTimer() {
        return closeRangeTimer;
    }

    public void setCloseRangeTimer(Timer closeRangeTimer) {
        this.closeRangeTimer = closeRangeTimer;
    }

    public boolean isCanCloseRange() {
        return canCloseRange;
    }

    public void setCanCloseRange(boolean canCloseRange) {
        this.canCloseRange = canCloseRange;
    }
}
