package game.handler;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.GameView;
import game.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class WorldEventHandler implements StepListener {
    private Level level;
    private GameView view;

    public WorldEventHandler(Level level, GameView view) {
        this.level = level;
        this.view = view;
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // view.setCentre(new Vec2(0, 0));
    }

    @Override
    public void preStep(StepEvent stepEvent) {}
}
