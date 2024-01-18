package game.entity.pickup;

import city.cs.engine.BodyImage;
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
public class CoinPickup extends Pickup {
    private static final Shape COIN_SHAPE = new BoxShape(1f, 1f);
    private static final BodyImage[] COIN_FRAMES = SpriteLoader.loadSprites("data/images/pickup/coin/", 1.5f);

    public CoinPickup(Level level, Vec2 position) {
        super(level, position, COIN_SHAPE, COIN_FRAMES);
    }

    @Override
    public void applyPickup(CollisionEvent e) {
        Player p = (Player) e.getReportingBody();
        p.setCoins(p.getCoins() + 5);
        e.getOtherBody().destroy();
    }

    @Override
    public void stopTimer(String timerId) {}

    @Override
    public void loadTimers(HashMap<String, Timer> timers) {}
}
