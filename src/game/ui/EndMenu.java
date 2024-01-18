package game.ui;

import game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author      Hubert Stoklosa hubert.stoklosa@city.ac.uk
 * @version     2.0
 * @since       2.0
 */
public class EndMenu {

    private JPanel endPanel;
    private JButton quitButton;
    private JLabel endText;
    private JPanel innerPanel;
    private JLabel endText2;
    private JButton mmButton;
    private JLabel endText3;

    public EndMenu(Game game) {
        endPanel.setBackground(Color.WHITE);
        endPanel.setPreferredSize(new Dimension(800, 600));

        innerPanel.setBackground(Color.WHITE);

        Font titleFont = Game.font.deriveFont(32f);
        Border titleMargin = BorderFactory.createEmptyBorder(0, 0, 20, 0);
        endText.setText("GAME COMPLETED");
        endText.setFont(titleFont);
        endText.setBorder(titleMargin);

        Font subTitleFont = Game.font.deriveFont(16f);
        endText2.setText("Thank you for playing " + game.getLevel().getPlayer().getName() + "!");
        endText2.setFont(subTitleFont);
        endText2.setBorder(titleMargin);

        Font textFont = Game.font.deriveFont(16f);
        endText3.setText("Congratulations! You have collected " + game.getLevel().getPlayer().getCoins() + " coins!");
        endText3.setFont(textFont);
        endText3.setBorder(titleMargin);

        mmButton.setBackground(Color.WHITE);
        mmButton.addActionListener(e -> {
            System.exit(0);
        });

        quitButton.setBackground(Color.WHITE);
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public JPanel getMainPanel() {
        return endPanel;
    }
}
