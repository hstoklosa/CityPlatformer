package game.handler;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.entity.enemy.ShootingEnemy;
import game.entity.pickup.ChestPickup;
import game.entity.projectile.Projectile;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class ProjectileImpactHandler implements CollisionListener {

    @Override
    public void collide(CollisionEvent e) {
        Projectile projectile = (Projectile) e.getReportingBody();
        e.getReportingBody().destroy();

        // Remove enemy hp on player's projectile collision
        if (e.getOtherBody() instanceof Enemy enemy && projectile.getFrom() instanceof Player) {
            enemy.setHealth(enemy.getHealth() - projectile.getType().getDamageDealt());
            enemy.getRunTimer().stop();
            enemy.getHitTimer().start();

            if (enemy.getHealth() <= 0)
                enemy.destroy();
        }

        if (e.getOtherBody() instanceof ChestPickup cp && projectile.getFrom() instanceof Player) {
            if (cp.getHits() > 1) {
                cp.setHits(cp.getHits() - 1);
                cp.getHitTimer().start();
                e.getReportingBody().destroy();
                return;
            }

            cp.destroy();
            cp.applyPickup(e);
        }

        if (e.getOtherBody() instanceof Player player && projectile.getFrom() instanceof ShootingEnemy) {
            player.setHealth(player.getHealth() - projectile.getType().getDamageDealt());
        }
    }
}