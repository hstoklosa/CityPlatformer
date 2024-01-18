package game.entity.platform;

import city.cs.engine.*;
import game.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class LiftPlatform extends Platform implements StepListener {
    private static final Shape liftShape = new BoxShape(2, 0.2f);

    public enum Type { VERTICAL, HORIZONTAL}
    private final Type type;

    private final Vec2 startPosition;
    private final float end, start;
    private float delta;

    public LiftPlatform(Level level, Vec2 startPosition, Type type, int distance) {
        super(level, liftShape);

        this.type = type;
        this.startPosition = startPosition;
        this.delta = 0.08f;

        this.start = type == Type.VERTICAL ?  startPosition.y : startPosition.x;
        this.end = type == Type.VERTICAL ?  startPosition.y + distance : startPosition.x + distance;

        this.setPosition(startPosition);
        level.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if ((type == Type.VERTICAL && getPosition().y < start)) {
            this.setPosition(startPosition);
            delta *= -1;
        }

        if ((type == Type.HORIZONTAL && getPosition().x < start)) {
            this.setPosition(startPosition);
            delta *= -1;
        }

        if ((type == Type.VERTICAL && getPosition().y > end)) {
            delta *= -1;
        }

        if (type == Type.HORIZONTAL && getPosition().x > end) {
            delta *= -1;
        }

        Vec2 newPosition = this.type == Type.VERTICAL ?
                new Vec2(this.getPosition().x, this.getPosition().y + delta) :
                new Vec2(this.getPosition().x + delta, this.getPosition().y);

        this.setPosition(newPosition);
    }

    @Override
    public void postStep(StepEvent stepEvent) {}
}
