package game.handler;

import city.cs.engine.DynamicBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.entity.enemy.Enemy;
import game.entity.enemy.SimpleEnemy;
import game.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class EnemyRegionHandler implements StepListener {
    private Level level;

    public EnemyRegionHandler(Level world) {
        this.level = world;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        for (DynamicBody body : level.getDynamicBodies()) {
            if (body instanceof SimpleEnemy) {
                Vec2 enemyPos = body.getPosition();
                Vec2 playerPos = level.getPlayer().getPosition();

                float difference = playerPos.sub(enemyPos).length();

                if (difference <= 10) {
                    level.removeStepListener(((Enemy) body).getWalkListener());
                    // System.out.println("in range :)");

                    Vec2 targetVector = new Vec2(playerPos.x - enemyPos.x, playerPos.y - enemyPos.y);
                    targetVector.normalize();

                    float walkingSpeed = 8f;
                    Vec2 walkingVelocity = targetVector.mul(walkingSpeed);

                    body.setLinearVelocity(walkingVelocity);
                }
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {}
}
