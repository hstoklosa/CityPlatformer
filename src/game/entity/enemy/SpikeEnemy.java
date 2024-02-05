package game.entity.enemy;

import city.cs.engine.*;
import game.entity.Player;
import game.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class SpikeEnemy extends StaticBody {
    private static final Shape SPIKE_SHAPE = new BoxShape(1, 0.25f);
    private static final BodyImage SPIKE_IMAGE = new BodyImage("data/images/enemy/spike.png", 2f);
    private AttachedImage attachedImg;

    public SpikeEnemy(Level level, Vec2 position) {
        super(level, SPIKE_SHAPE);
        this.setPosition(position);
        this.attachedImg = addImage(SPIKE_IMAGE);

        SolidFixture sf = new SolidFixture(this, SPIKE_SHAPE);
        sf.setRestitution(0.75f);

        Sensor sensor = new Sensor(this, SPIKE_SHAPE);
        sensor.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent e) {
                if (e.getContactBody() instanceof Player player) {
                    player.setHealth(player.getHealth() - 20);
                }
            }

            @Override
            public void endContact(SensorEvent sensorEvent) {}
        });

        // setAlwaysOutline(true);
    }
}
