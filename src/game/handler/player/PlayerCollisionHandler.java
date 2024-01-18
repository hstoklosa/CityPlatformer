package game.handler.player;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.entity.pickup.ChestPickup;
import game.entity.pickup.Pickup;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class PlayerCollisionHandler implements CollisionListener {
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            Player p = (Player) e.getReportingBody();
            p.setHealth(p.getHealth() - 5);
        }

        if (e.getOtherBody() instanceof Pickup && !(e.getOtherBody() instanceof ChestPickup)) {
            Pickup p = (Pickup) e.getOtherBody();
            Player.COLLECT_SOUND.play();
            p.applyPickup(e);
        }
    }
}
