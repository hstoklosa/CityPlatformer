package game.entity.enemy;

import city.cs.engine.*;
import game.entity.Character;
import game.entity.Player;
import game.entity.pickup.CoinPickup;
import game.handler.animation.Animatable;
import game.levels.Level;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public abstract class Enemy extends Character implements Animatable {
    private int health = 100;

    protected AttachedImage attachedImg;
    public HashMap<String, Timer> timers = new HashMap<>();


    private StepListener walkListener;
    private Timer hitTimer;
    private Timer runTimer;


    public Enemy(Level level, Vec2 position, Shape enemyShape) {
        super(level, enemyShape);

        this.loadTimers(timers);
        this.setPosition(position);
        
        SolidFixture sf = new SolidFixture(this, enemyShape);
        // sf.setRestitution(1f);
    }

    @Override
    public void changeImage(BodyImage newImage) {
        AttachedImage oldImage = attachedImg;

        this.removeAllImages();
        this.removeAttachedImage(this.attachedImg);
        attachedImg = this.addImage(newImage);

        if (oldImage != null && oldImage.isFlippedHorizontal()) {
            this.attachedImg.flipHorizontal();
        };
    }

    @Override
    public void destroy() {
        new CoinPickup((Level) this.getWorld(), this.getPosition());
        super.destroy();
    }

    public StepListener getWalkListener() {
        return walkListener;
    }

    public void setStepListener(StepListener listener) {
        this.walkListener = listener;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Timer getHitTimer() {
        return hitTimer;
    }

    public void setHitTimer(Timer hitTimer) {
        this.hitTimer = hitTimer;
    }

    public Timer getRunTimer() {
        return runTimer;
    }

    public void setRunTimer(Timer runTimer) {
        this.runTimer = runTimer;
    }

    public HashMap<String, Timer> getTimers() {
        return timers;
    }
}
