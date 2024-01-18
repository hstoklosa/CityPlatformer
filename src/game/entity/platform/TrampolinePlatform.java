package game.entity.platform;

import city.cs.engine.*;
import game.entity.Player;
import game.handler.animation.Animatable;
import game.handler.animation.AnimationHandler;
import game.levels.Level;
import game.utils.SpriteLoader;

import javax.swing.*;
import java.util.HashMap;

public class TrampolinePlatform extends Platform implements Animatable {
    private static final Shape shape = new BoxShape(1, 0.75f);
    private static final BodyImage TRAMPOLINE_IDLE = new BodyImage("data/images/platform/idle.png", 3f);
    private AttachedImage attachedImg;

    HashMap<String, Timer> timers = new HashMap<>();

    private Timer bounceTimer;

    public TrampolinePlatform(Level level) {
        super(level, shape);

        SolidFixture sf = new SolidFixture(this, shape);
        sf.setRestitution(1.5f);

        this.attachedImg = addImage(TRAMPOLINE_IDLE);

        final BodyImage[] BOUNCE_FRAMES = SpriteLoader.loadSprites("data/images/platform/trampoline/");
        bounceTimer = new Timer(50, new AnimationHandler("bounce", this, BOUNCE_FRAMES, true));
        timers.put("bounce", bounceTimer);

        this.addCollisionListener(e -> {
            if (e.getOtherBody() instanceof Player) {
                bounceTimer.start();
            }
        });

    }

    @Override
    public void changeImage(BodyImage newImage) {
        removeAllImages();
        this.attachedImg = addImage(newImage);
    }

    @Override
    public void stopTimer(String timerId) {
        this.removeAllImages();
        this.timers.get(timerId).stop();

        switch (timerId) {
            case "bounce" -> {
                this.attachedImg = addImage(TRAMPOLINE_IDLE);
            }
            default -> {}
        }
    }

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}
}
