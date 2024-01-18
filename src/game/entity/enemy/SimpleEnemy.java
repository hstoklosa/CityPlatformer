package game.entity.enemy;

import city.cs.engine.*;
import game.handler.animation.AnimationHandler;

import game.levels.Level;
import game.utils.SpriteLoader;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class SimpleEnemy extends Enemy implements StepListener {
    private static final Shape ENEMY_SHAPE = new BoxShape(1, 1.5f);
    static final BodyImage[] RUN_FRAMES = SpriteLoader.loadSprites("data/images/enemy/run/");
    static final BodyImage[] HIT_FRAMES = SpriteLoader.loadSprites("data/images/enemy/hit/");

    private final int SPEED = 4;
    private final int RANGE = 3;

    private float left, right;

    public SimpleEnemy(Level level, Vec2 position) {
        super(level, position, ENEMY_SHAPE);
        super.setStepListener(this);

        level.addStepListener(this);
        startWalking(this.SPEED);
    }

    @Override
    public void setPosition(Vec2 position) {
        super.setPosition(position);

        left = position.x - RANGE;
        right = position.x + RANGE;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        AttachedImage attachedImg = super.attachedImg;

        if (getPosition().x > right){
            startWalking(-SPEED);

            attachedImg.flipHorizontal();
            if (!(attachedImg.isFlippedHorizontal())) {
                attachedImg.flipHorizontal();
            }
        }

        if (getPosition().x < left){
            startWalking(SPEED);

            if (attachedImg.isFlippedHorizontal()) {
                attachedImg.flipHorizontal();
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {}

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {
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

        System.out.println(this.getImages().size());
    }
}
