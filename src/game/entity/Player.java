package game.entity;

import city.cs.engine.*;
import game.entity.projectile.Projectile;
import game.handler.animation.Animatable;
import game.handler.animation.AnimationHandler;
import game.handler.player.PlayerCollisionHandler;
import game.utils.SpriteLoader;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class Player extends Character implements Animatable {
    public static final Shape PLAYER_SHAPE = new BoxShape(1, 1.5f);
    public static final BodyImage PLAYER_JUMP = new BodyImage("data/images/player/jump.png", 3f);
    public static final BodyImage PLAYER_FALL = new BodyImage("data/images/player/fall.png", 3f);

    public static final int MAX_DOUBLE_JUMPS = 2;
    public static final float DEFAULT_MOVE_SPEED = 8f;

    public static SoundClip SPAWN_SOUND, JUMP_SOUND, SHOOT_SOUND, COLLECT_SOUND;

    public enum State { IDLE, WALKING, RUNNING, JUMPING, DOUBLE_JUMPING, FALLING }
    private State currentState;
    public HashMap<State, Timer> timers;
    public HashMap<String, Timer> timerss;
    private AttachedImage attachedImg;

    private int health = 100;
    private int stamina = 100;
    private int coins = 0;
    private float moveSpeed = 8f;
    private float jumpSpeed = 6f;
    private int doubleJumpsLeft = 2;

    private Timer staminaRegen;

    public Player(World world) {
        super(world, PLAYER_SHAPE);

        // Setup player state & animation
        final BodyImage[] IDLE_FRAMES = SpriteLoader.loadSprites("data/images/player/idle/");
        final BodyImage[] MOVE_FRAMES = SpriteLoader.loadSprites("data/images/player/move/");
        final BodyImage[] DOUBLE_JUMP_FRAMES = SpriteLoader.loadSprites("data/images/player/double_jump");

        timers = new HashMap<>();
        timerss = new HashMap<>();
        currentState = State.IDLE;

        AnimationHandler idle = new AnimationHandler("idle", this, IDLE_FRAMES);
        timers.put(State.IDLE, new Timer(50, idle));

        AnimationHandler move = new AnimationHandler("move", this, MOVE_FRAMES);
        timers.put(State.WALKING, new Timer(50, move));
        timers.put(State.RUNNING, new Timer(35, move));

        AnimationHandler double_jump = new AnimationHandler("double_jump", this, DOUBLE_JUMP_FRAMES, true);
        Timer djTimer = new Timer(50, double_jump);
        timers.put(State.DOUBLE_JUMPING, djTimer);
        timerss.put("double_jump", djTimer);

        timers.get(State.IDLE).start();

        // Stamina regeneration timer
        staminaRegen = new Timer(500, e -> {
            if (currentState != State.RUNNING) {
                if (stamina == 100) return;

                stamina += 1;
                return;
            }

            stamina -= 3;
        });

        staminaRegen.start();

        this.addCollisionListener(new PlayerCollisionHandler());
    }

    static {
        try {
            JUMP_SOUND = new SoundClip("data/audio/jump.wav");
            SPAWN_SOUND = new SoundClip("data/audio/spawn.wav");
            SHOOT_SOUND = new SoundClip("data/audio/shoot.wav");
            COLLECT_SOUND = new SoundClip("data/audio/collect.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    @Override
    public void shootProjectile(Vec2 towards, Projectile projectile) {
        super.shootProjectile(towards, projectile);
        SHOOT_SOUND.play();
    }

    /**
     * A modified method from the Walker object.
     * <p>
     * The new method contains the startWalking implementation from the superclass, but flips the orientation of the player image based on left/right movement (accomplished using speed +/-)
     *
     * @param  speed the speed at which the player is currently moving on
     * @return void at all times
     */
    @Override
    public void startWalking(float speed) {
        super.startWalking(speed);

        // change orientation
        if (speed == 0) return;

        if (speed < 0) {
            if (!attachedImg.isFlippedHorizontal()) attachedImg.flipHorizontal();
        } else {
            if (attachedImg.isFlippedHorizontal()) attachedImg.flipHorizontal();
        }
    }

    /**
     * Creates and moves a projectile from a body.
     * <p>
     * This implementation creates a new Projectile object, and calculates the direction & speed in which the projectile should go based on the towards parameter. There is an offset for the projectile so the body doesn't block it, then the direction & speed is applied to it.
     *
     * @param  newImage the (x, y) coordinates where the projectile should go
     * @return void at all times
     */
    @Override
    public void changeImage(BodyImage newImage) {
        AttachedImage oldImage = this.getAttachedImg();

        this.removeAllImages();
        this.removeAttachedImage(this.attachedImg);
        this.attachedImg = this.addImage(newImage);

        if (oldImage != null && oldImage.isFlippedHorizontal()) {
            this.attachedImg.flipHorizontal();
        }
    }

    @Override
    public void stopTimer(String timerId) {
        this.removeAllImages();

        switch (timerId) {
            case "double_jump" -> {
                timerss.get(timerId).stop();
                this.currentState = State.FALLING;
            }
            default -> {}
        }
    }

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}

    @Override
    public void setPosition(Vec2 position) {
        SPAWN_SOUND.play();
        super.setPosition(position);
    }


    // Getters & Setters for Player
    public Timer getStaminaRegen() {
        return staminaRegen;
    }

    public void setStaminaRegen(Timer staminaRegen) {
        this.staminaRegen = staminaRegen;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getDoubleJumpsLeft() {
        return doubleJumpsLeft;
    }

    public void setDoubleJumpsLeft(int doubleJumpsLeft) {
        this.doubleJumpsLeft = doubleJumpsLeft;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public AttachedImage getAttachedImg() {
        return attachedImg;
    }

    public void setAttachedImg(AttachedImage attachedImg) {
        this.attachedImg = attachedImg;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }
}