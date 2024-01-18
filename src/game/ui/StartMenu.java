package game.ui;

import game.Game;
import game.GameSaverLoader;
import game.levels.Level;
import game.utils.TextPrompt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class StartMenu {
    private JPanel startPanel;
    private JButton startButton;
    private JTextField nameField;
    private JPanel keyboardPanel;
    private JButton loadGameButton;
    private JLabel titleLabel;
    private JButton highScoresButton;
    private JButton quitButton;
    private JLabel imageLabel;
    private JButton controlsButton;

    private JPanel highScorePanel;
    private JPanel controlsPanel;

    private Game game;

    public StartMenu(Game game) {
        this.game = game;
        this.highScorePanel = new LeaderboardPanel(game, this.getMainPanel());
        this.controlsPanel = new ControlsPanel(game, this.getMainPanel());

        keyboardPanel.setBackground(Color.WHITE);
        startPanel.setBackground(Color.WHITE);
        startPanel.setPreferredSize(new Dimension(800, 600));

        imageLabel.setIcon(Game.createScaledImage("data/images/player/jump.png", 96));

        // Main menu title
        Font titleFont = Game.font.deriveFont(32f);
        Border titleMargin = BorderFactory.createEmptyBorder(20, 0, 20, 0);
        titleLabel.setText("CITY PLATFORMER");
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(titleMargin);

        // Buttons styling
        Font buttonFont = Game.font.deriveFont(10f);
        Insets buttonMargin = new Insets(10, 10, 10, 10);

        startButton.setFont(buttonFont);
        startButton.setBackground(Color.WHITE);
        startButton.setMargin(buttonMargin);

        loadGameButton.setFont(buttonFont);
        loadGameButton.setBackground(Color.WHITE);
        loadGameButton.setMargin(buttonMargin);

        highScoresButton.setFont(buttonFont);
        highScoresButton.setBackground(Color.WHITE);
        highScoresButton.setMargin(buttonMargin);

        quitButton.setFont(buttonFont);
        quitButton.setBackground(Color.WHITE);
        quitButton.setMargin(buttonMargin);

        controlsButton.setFont(buttonFont);
        controlsButton.setBackground(Color.WHITE);
        controlsButton.setMargin(buttonMargin);

        // placeholder for the input - source in utils
        TextPrompt tp = new TextPrompt("Enter your username...", nameField);
        Insets textFieldPadding = new Insets(10, 5, 10, 5);
        Border textFieldMargin = BorderFactory.createEmptyBorder(0, 0, 20, 0);
        nameField.setMargin(textFieldPadding);

        // combine original and new border
        nameField.setBorder(BorderFactory.createCompoundBorder(textFieldMargin, nameField.getBorder()));


        // button to start the game with the given nickname
        startButton.addActionListener(e -> {
            if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(game, "Enter you nickname!");
                return;
            }

            game.startGame(nameField.getText());
        });

        // button to load the latest state
        loadGameButton.addActionListener(e -> {
            try {
                Level loadedLevel = GameSaverLoader.load(game);
                game.startGame(nameField.getText(), loadedLevel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        controlsButton.addActionListener(e -> {
            game.switchPanel(controlsPanel, this.getMainPanel());
        });

        highScoresButton.addActionListener(e -> {
            game.switchPanel(highScorePanel, this.getMainPanel());
        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public JPanel getMainPanel() {
        return startPanel;
    }
}
