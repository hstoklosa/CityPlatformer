package game.handler.player;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.Game;
import game.GameView;
import game.entity.Player;
import game.handler.animation.AnimationHandler;
import game.ui.DeathMenu;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class PlayerStateHandler implements StepListener {
    private Player player;
    private GameView view;
    private Game game;

    public PlayerStateHandler(Player player, GameView view, Game game) {
        this.player = player;
        this.view = view;
        this.game = game;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        Player.State state = player.getCurrentState();
        HashMap<Player.State, Timer> timers = player.timers;

        if (player.getHealth() <= 0) {
            game.handlePlayerDeath();
        }

        // System.out.println(p.getDoubleJumpsLeft());
        switch (state) {
            case IDLE:
                player.setMoveSpeed(0);
                break;
            case WALKING:
                player.setMoveSpeed(Player.DEFAULT_MOVE_SPEED);
                break;
            case RUNNING:
                player.setMoveSpeed(Player.DEFAULT_MOVE_SPEED + 2);
                break;
            case JUMPING:
                player.changeImage(Player.PLAYER_JUMP);
                break;
            case FALLING:
                player.changeImage(Player.PLAYER_FALL);
                break;
            default:
                break;
        }


        for (Map.Entry<Player.State, Timer> timerEntry : player.timers.entrySet()) {
            Player.State timerKey = timerEntry.getKey();
            Timer timer = timerEntry.getValue();

            // Start and stop appropriately based on the Player state
            if (timerKey == state) { timer.start(); } else { timer.stop(); }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {}
}
