package game.entity.projectile;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.handler.ProjectileImpactHandler;
import game.levels.Level;
import game.entity.Character;

import javax.swing.*;
import java.awt.*;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class Projectile extends DynamicBody {
    private static final Shape PROJECTILE_SHAPE = new CircleShape(0.2f);
    private static final BodyImage PROJECTILE_IMAGE = new BodyImage("data/images/player/bullet.png", 0.6f);

    public enum Type {
        CLOSE(30),
        RANGE(15);

        private final int damageDealt;

        Type(int damageDealt) {
            this.damageDealt = damageDealt;
        }

        public int getDamageDealt() {
            return damageDealt;
        }
    };

    private Type type;
    private final Character from;

    public Projectile(Level world, Character from, Type type) {
        super(world, PROJECTILE_SHAPE);
        this.from = from;
        this.type = type;

        this.setBullet(true);
        this.addCollisionListener(new ProjectileImpactHandler());

        SolidFixture ballFixture = new SolidFixture(this, PROJECTILE_SHAPE);
        ballFixture.setRestitution(1);
        ballFixture.setFriction(0);

        if (type == Type.RANGE) {
            setGravityScale(0);
            this.addImage(PROJECTILE_IMAGE);
        }

        if (type == Type.CLOSE) {
            this.setFillColor(Color.BLACK);
        }
    }

    public Character getFrom() {
        return from;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
