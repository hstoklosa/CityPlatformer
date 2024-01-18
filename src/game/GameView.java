package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.UserView;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Camera to the game world.
 *
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class GameView extends UserView {
    private static final Image COIN_ICON = new ImageIcon("./data/images/coin.png").getImage();
    private Level level;

    public GameView(Level level, int width, int height) {
        super(level, width, height);
        this.level = level;
        // setGridResolution(0.5f);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(level.background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        handleEnemyGraphics(g);
        handlePlayerGraphics(g);
    }

    public void handleEnemyGraphics(Graphics2D g) {
        // Draw HP bar above enemies
        List<DynamicBody> bodies = level.getDynamicBodies();
        for (DynamicBody body : bodies) {
            if (body instanceof Enemy) {
                Enemy e = (Enemy) body;
                Point2D viewPos = this.worldToView(e.getPosition());

                // Border
                g.setColor(Color.BLACK);
                g.drawRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 40, 50, 5);

                // Filled healthbar
                int fillWidthEnemy = (int) ((e.getHealth() / 100.0) * 50);
                g.setColor(Color.RED);
                g.fillRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 40, fillWidthEnemy, 5);
            }
        }
    }

    public void handlePlayerGraphics(Graphics2D g) {
        Player player = level.getPlayer();

        // Display nickname above the player character
        Point2D viewPos = this.worldToView(player.getPosition());

        FontMetrics fm = g.getFontMetrics(); // get the font metrics for the current font
        int textWidth = fm.stringWidth(player.getName()); // calculate the width of the player's name
        int centeredX = (int) viewPos.getX() - textWidth / 2;

        // Font newFont = g.getFont().deriveFont(14);
        Font newFont = new Font(null, Font.BOLD, 12);
        g.setFont(newFont);
        g.setColor(Color.BLACK);
        g.drawString(player.getName(), centeredX, (int) (viewPos.getY() - 55));


        // Draw player statistics in the corner
        Font font = new Font("Arial", Font.PLAIN, 24); // set font to Arial, plain style, size 24
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawImage(COIN_ICON, 30, 30, 30, 30, this);
        g.drawString(String.valueOf(player.getCoins()), 70, 53);


        // Border
        g.setColor(Color.BLACK);
        g.drawRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 45, 50, 5);

        // Filled healthbar
        int fillWidthEnemy = (int) ((player.getHealth() / 100.0) * 50);
        g.setColor(Color.RED);
        g.fillRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 45, fillWidthEnemy, 5);

        // Border
        g.setColor(Color.BLACK);
        g.drawRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 35, 50, 5);

        // Filled healthbar
        int staminaFill = (int) ((player.getStamina() / 100.0) * 50);
        g.setColor(Color.ORANGE);
        g.fillRect((int) viewPos.getX() - 25, (int) viewPos.getY() - 35, staminaFill, 5);

    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
