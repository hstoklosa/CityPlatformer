package game.handler.player;

import game.Game;
import game.GameSaverLoader;
import game.entity.Player;
import game.GameView;
import game.entity.projectile.Projectile;
import game.levels.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class PlayerInputHandler implements KeyListener, MouseListener {
    private Game game;
    private GameView view;
    private Player player;

    boolean ctrlPressed = false;

    public PlayerInputHandler(Game game, Level level, GameView view) {
        this.game = game;
        this.view = view;
        this.player = level.getPlayer();
    }

    public void updatePlayer(Player player) {
        this.player = player;
    }

    // Keyboard Events
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        Player.State currentState = player.getCurrentState();

        // other key commands are omitted
        switch (code) {
            // Move left
            case KeyEvent.VK_A:
                player.setMoveSpeed(Player.DEFAULT_MOVE_SPEED);
                player.startWalking(-(player.getMoveSpeed()));

                // stop running animation in the air
                if (currentState == Player.State.FALLING || currentState == Player.State.JUMPING || currentState == Player.State.DOUBLE_JUMPING) {
                    return;
                }

                // check if player started running
                if (ctrlPressed && (currentState != Player.State.JUMPING || currentState != Player.State.DOUBLE_JUMPING)) {
                    player.setCurrentState(Player.State.RUNNING);
                    player.startWalking(-(player.getMoveSpeed()));
                    break;
                }

                player.setCurrentState(Player.State.WALKING);
                break;
            // Move right
            case KeyEvent.VK_D:
                player.setMoveSpeed(Player.DEFAULT_MOVE_SPEED);
                player.startWalking(player.getMoveSpeed());

                // stop running animation in the air
                if (currentState == Player.State.FALLING || currentState == Player.State.JUMPING || currentState == Player.State.DOUBLE_JUMPING) {
                    return;
                }

                // check if player started running
                if (ctrlPressed && (currentState != Player.State.JUMPING || currentState != Player.State.DOUBLE_JUMPING)) {
                    player.setCurrentState(Player.State.RUNNING);
                    player.startWalking(player.getMoveSpeed());
                    break;
                }

                player.setCurrentState(Player.State.WALKING);
                break;
            // Sprinting
            case KeyEvent.VK_CONTROL:
                if (currentState == Player.State.WALKING) {
                    player.setCurrentState(Player.State.RUNNING);
                }

                ctrlPressed = true;
                break;
            // Jumping
            case KeyEvent.VK_SPACE:
                 if (currentState == Player.State.DOUBLE_JUMPING || currentState == Player.State.JUMPING) {
                     Player.JUMP_SOUND.play();
                 }

                if (currentState == Player.State.JUMPING && player.getDoubleJumpsLeft() > 0) {
                    player.setLinearVelocity(new Vec2(0, player.getJumpSpeed()));
                    player.setDoubleJumpsLeft(player.getDoubleJumpsLeft() - 1);
                    player.setCurrentState(Player.State.DOUBLE_JUMPING);
                    break;
                }

                player.jump(player.getJumpSpeed());
                player.setCurrentState(Player.State.JUMPING);
                break;
            // Save state
            case KeyEvent.VK_S:
                try {
                    GameSaverLoader.save((Level) player.getWorld());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            // Load state
            case KeyEvent.VK_L:
                try {
                    Level loadedLevel = GameSaverLoader.load(game);
                    game.getLevel().stop();
                    game.setLevel(loadedLevel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            // Restart current level
            case KeyEvent.VK_R:
                String currentLevel = game.getLevel().getLevelName();

                switch (currentLevel) {
                    case "Level1" -> game.setLevel(new Level1(game));
                    case "Level2" -> game.setLevel(new Level2(game));
                    case "Level3" -> game.setLevel(new Level3(game));
                    default -> {}
                }
            // Pause the game
            case KeyEvent.VK_ESCAPE:
                JPanel pp = game.getPauseMenu().getMainPanel();

                if (pp.isVisible()) {
                    game.getLevel().start();
                    Level.BACKGROUND.resume();
                    pp.setVisible(false);
                    return;
                }

                Level.BACKGROUND.pause();
                pp.setVisible(true);
                game.getLevel().stop();
            default:
                break;
        }
    }

    // Mouse Events
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        Player.State currentState = player.getCurrentState();

        switch (code) {
            // Stop running
            case KeyEvent.VK_A, KeyEvent.VK_D -> {
                player.startWalking(0);

                if (currentState == Player.State.FALLING || currentState == Player.State.JUMPING || currentState == Player.State.DOUBLE_JUMPING) {
                    break;
                }

                player.setCurrentState(Player.State.IDLE);
            }
            // Stop sprinting
            case KeyEvent.VK_CONTROL -> {
                if (currentState == Player.State.RUNNING) {
                    player.setCurrentState(Player.State.WALKING);
                }

                ctrlPressed = false;
            }
            default -> {}
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();

        // TODO: 2 TYPES OF SHOOTING FOR THE PLAYER
        switch (code) {
            case MouseEvent.BUTTON1 -> {
                if (player.isCanCloseRange()) {
                    player.shootProjectile(view.viewToWorld(e.getPoint()), new Projectile(this.game.getLevel(), this.player, Projectile.Type.CLOSE));
                    player.setCanCloseRange(false);
                    player.getCloseRangeTimer().start();
                }
            }
            case MouseEvent.BUTTON3 -> {
                player.shootProjectile(view.viewToWorld(e.getPoint()), new Projectile(this.game.getLevel(), this.player, Projectile.Type.RANGE));
            }
            default -> {}
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        view.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {}
}
