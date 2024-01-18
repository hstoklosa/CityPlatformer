package game;

import city.cs.engine.DebugViewer;
import game.entity.Player;
import game.handler.EnemyRegionHandler;
import game.handler.player.PlayerInputHandler;
import game.handler.player.PlayerStateHandler;
import game.handler.WorldEventHandler;
import game.levels.*;
import game.ui.DeathMenu;
import game.ui.EndMenu;
import game.ui.PauseMenu;
import game.ui.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;


/**
 * Your main game entry point
 *
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       1.0
 */
public class Game extends JFrame {
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    public static final ImageIcon CLOSE_IMAGE = Game.createScaledImage("data/images/controls/close.png", 46);
    public static final ImageIcon BACK_IMAGE = Game.createScaledImage("data/images/controls/back.png", 46);
    public static final Font font;

    private final StartMenu startMenu;
    private PauseMenu pauseMenu;
    public EndMenu endMenu;
    private JLayeredPane layeredPanel;

    private Level level;
    private PlayerInputHandler inputHandler;
    private GameView view;


    /** Initialise a new Game. */
    public Game() {
        super("City Platformer");

        // Prepare JFrame and display main menu
        setLayout(new BorderLayout());
        this.startMenu = new StartMenu(this);

         add(startMenu.getMainPanel(), BorderLayout.CENTER);
         // startGame("exotic");

        setPreferredSize(new Dimension(800, 600)); // up??
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows frame to exit the app
        setLocationByPlatform(true);
        setResizable(false); // don't let the frame be resized
        pack(); // size the frame to fit the world view
        setVisible(true); // make the frame visible
    }

    static {
        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("data/PressStart2P-Regular.ttf")
            );
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGame(String nickname) {
        startGame(nickname, new Level1(this));
        this.level.populate();
    }

    /**
     * Force set a level into the view.
     * <p>
     * This implementation copies user's attributes onto the new body on a new Physics World and then hooks onto the GameView (world's camera).
     *
     * @param  level the desired Level object to be loaded into the view
     */
    public void startGame(String nickname, Level level) {
        this.level = level;

        // Make an empty game world & hook a "view" into it
        // Prepare the frame (GameView, Panels, Settings)
        layeredPanel = new JLayeredPane();

        view = new GameView(level, GAME_WIDTH, GAME_HEIGHT);
        view.setBounds(0, 0, 800, 600);

        // Pause menu on escape button
        pauseMenu = new PauseMenu(this);

        layeredPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = layeredPanel.getWidth();
                int height = layeredPanel.getHeight();
                int paletteWidth = pauseMenu.getMainPanel().getWidth();
                int paletteHeight = pauseMenu.getMainPanel().getHeight();

                int newX = (width - paletteWidth) / 2;
                int newY = (height - paletteHeight) / 2;

                pauseMenu.getMainPanel().setLocation(newX, newY);
            }
        });

        layeredPanel.add(view, JLayeredPane.DEFAULT_LAYER);
        layeredPanel.add(pauseMenu.getMainPanel(), JLayeredPane.PALETTE_LAYER);

        inputHandler = new PlayerInputHandler(this, level, view);
        view.addKeyListener(inputHandler);
        view.addMouseListener(inputHandler);

        level.getPlayer().setName(nickname); // set player's nickname
        level.addStepListener(new WorldEventHandler(level, view));
        level.addStepListener(new PlayerStateHandler(level.getPlayer(), view, this));
        level.addStepListener(new EnemyRegionHandler(level));

        // this.add(view, BorderLayout.CENTER);
        switchPanel(layeredPanel, startMenu.getMainPanel());

        // Optional: uncomment this to make a debugging view
         JFrame debugView = new DebugViewer(level, 500, 500);

        // Optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // Start the simulation & request instant focus on the view (required for events)
        level.start();
        view.requestFocus();
    }

    /**
     * Sets the current level the player was on.
     */
    public void restartGame() {
        String currentLevel = this.level.getLevelName();

        switch (currentLevel) {
            case "Level1" -> setLevel(new Level1(this));
            case "Level2" -> setLevel(new Level2(this));
            case "Level3" -> setLevel(new Level3(this));
            default -> {}
        }
    }

    public void handlePlayerDeath() {
        DeathMenu dm = new DeathMenu(this);
        switchPanel(dm.getMainPanel(), layeredPanel);
    }

    /**
     * End of game logic.
     * <p>
     * This method stores the nickname and score for the player that just finished playing, and then switches to endMenu panel.
     *
     * @throws IOException if an input or output exception occurred
     */
    public void endGame() throws IOException {
        String nickname = this.level.getPlayer().getName();
        int coins = this.level.getPlayer().getCoins();

        GameHighScore.writeHighScore(nickname, coins);
        endMenu = new EndMenu(this);

        switchPanel(endMenu.getMainPanel(), layeredPanel);
    }

    /**
     * Chooses the next level based on the current one.
     */
    public void nextLevel() throws IOException {
        switch (level.getLevelName()) {
            case "Level1" -> {
                setLevel(new Level2(this));
                this.level.populate();
            }
            case "Level2" -> {
                setLevel(new Level3(this));
                this.level.populate();
            }
            case "Level3" -> endGame();
            default -> {}
        }
    }

    /**
     * Force set a level into the view.
     * <p>
     * This implementation copies user's attributes onto the new body on a new Physics World and then hooks onto the GameView (world's camera).
     *
     * @param  level the desired Level object to be loaded into the view
     */
    public void setLevel(Level level) {
        Player newPlayer = level.getPlayer();
        Player oldPlayer = this.level.getPlayer();

        // copy player data from old level
        newPlayer.setName(oldPlayer.getName());
        newPlayer.setHealth(oldPlayer.getHealth());
        newPlayer.setCoins(oldPlayer.getCoins());
        newPlayer.setStamina(oldPlayer.getStamina());

        // setup new level (hook view, sync player, start)
        this.level.stop();
        this.level = level;

        view.setWorld(level);
        view.setLevel(level);
        inputHandler.updatePlayer(newPlayer);

        level.addStepListener(new WorldEventHandler(level, view));
        level.addStepListener(new PlayerStateHandler(newPlayer, view, this));
        level.addStepListener(new EnemyRegionHandler(level));

        this.level.start();
        view.requestFocus();
    }


    /**
     * Switches panels in a JFramed.
     * <p>
     * This implementation appropriately removes and adds panels, and then revalides with the JFrame. It helps to keep the code DRY by not having to repeat the same lines of code over again.
     *
     * @param  newPanel the JComponent to be added
     * @param  oldPanel the JComponent to be removed
     */
    public void switchPanel(JComponent newPanel, JComponent oldPanel) {
        this.remove(oldPanel);
        this.add(newPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * Produces an ImageIcon with respect to the height given by applying aspect ratio.
     * <p>
     * This method creates an ImageIcon of a specific size based on height. This is really helpful for cases where you need multiple objects of the same height without knowing the one of the dimensions.
     *
     * @param  path the
     * @param  height the
     * @return ImageIcon
     */
    public static ImageIcon createScaledImage(String path, int height) {
        ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it

        float aspectRatio = (float) image.getWidth(null) / image.getHeight(null);
        int autoWidth = (int) (height * aspectRatio);

        Image newImage = image.getScaledInstance(autoWidth, height, Image.SCALE_SMOOTH); // scale it the smooth way

        return new ImageIcon(newImage); // transform it back
    }

    // Getters & Setters
    public Level getLevel() {
        return level;
    }

    public JLayeredPane getLayeredPanel() {
        return layeredPanel;
    }

    public void setLayeredPanel(JLayeredPane layeredPanel) {
        this.layeredPanel = layeredPanel;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    public StartMenu getStartMenu() {
        return startMenu;
    }

    /** Run the game. */
    public static void main(String[] args) throws IOException {
        new Game();
    }
}

