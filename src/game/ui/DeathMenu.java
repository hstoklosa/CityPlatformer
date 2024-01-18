package game.ui;

import game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DeathMenu {
    private JPanel panel1;
    private JLabel textLabel;
    private JButton restartButton;
    private JButton quitButton;

    public DeathMenu(Game game) {
        Font titleFont = Game.font.deriveFont(32f);
        Border titleMargin = BorderFactory.createEmptyBorder(0, 0, 20, 0);
        textLabel.setText("YOU HAVE DIED!");
        textLabel.setFont(titleFont);
        textLabel.setBorder(titleMargin);

        quitButton.setBackground(Color.WHITE);
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public JPanel getMainPanel() {
        return panel1;
    }
}
