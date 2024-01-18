package game.entity.pickup;

import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.Shape;
import game.entity.Player;
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
public class MelonPickup extends Pickup {
    private static final Shape MELON_SHAPE = new BoxShape(1, 1);

    public MelonPickup(Level world, Vec2 position) {
        super(world, position, MELON_SHAPE, SpriteLoader.loadSprites("data/images/pickup/melon"));
    }

    @Override
    public void applyPickup(CollisionEvent e) {
        Player p = (Player) e.getReportingBody();

        p.setHealth(p.getHealth() + 5);
        e.getOtherBody().destroy();

        if (p.getHealth() >= 100) p.setHealth(100);
    }

    @Override
    public void stopTimer(String timerId) {}

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}
}
