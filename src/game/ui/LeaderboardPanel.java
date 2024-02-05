package game.ui;

import game.Game;
import game.GameHighScore;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel(Game game, JPanel startPanel) {
        super(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));

        JPanel scorePanel = new JPanel(new GridLayout(0, 2));
        //scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setPreferredSize(new Dimension(200, 400));
        scorePanel.setBackground(Color.WHITE);
        Border labelMargin = BorderFactory.createEmptyBorder(0, 0, 20, 0);

        try {
            HashMap<String, Integer> scores = GameHighScore.readScores();
            // System.out.println(entry.getKey()+" : "+entry.getValue());

            if (scores.size() > 0) {
                int baseFontSize = 24;
                int step = 2;
                int count = 0;

                for (HashMap.Entry<String, Integer> entry : scores.entrySet()) {
                    float fontSize;

                    if (count < 3) {
                        // Decrease font size for the first three entries
                        fontSize = baseFontSize - (step * (count));
                    } else {
                        // Constant font size for the rest
                        fontSize = baseFontSize / 2;
                    }

                    //  JLabel score = new JLabel((count < 3 ? String.valueOf(count + 1) + "." : "") + entry.getKey() + ":" + entry.getValue());

                    Font scoreFont = Game.font.deriveFont(fontSize);
                    JLabel keyLabel = new JLabel(((count + 1) + ".") + entry.getKey());
                    keyLabel.setFont(scoreFont);
                    keyLabel.setBorder(labelMargin);
                    keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    scorePanel.add(keyLabel);
                    
                    JLabel valueLabel = new JLabel(String.valueOf(entry.getValue()), SwingConstants.RIGHT);
                    valueLabel.setFont(scoreFont);
                    valueLabel.setBorder(labelMargin);
                    valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    scorePanel.add(valueLabel);

                    count++;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        scorePanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Create a JScrollPane containing the JPanel
        JScrollPane scrollPane = new JScrollPane(scorePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(600, 540));
        JButton backButton = new JButton();
        backButton.add(new JLabel(Game.createScaledImage("data/images/controls/back.png", 36)));

        backButton.addActionListener(event -> {
            game.switchPanel(startPanel, this);
        });

        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.weightx = 1;
        panelGbc.weighty = 1;
        panelGbc.fill = GridBagConstraints.NONE;
        panelGbc.anchor = GridBagConstraints.CENTER;

        // Set up GridBagConstraints for the button
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.weightx = 1;
        buttonGbc.weighty = 1;
        buttonGbc.fill = GridBagConstraints.NONE;
        buttonGbc.anchor = GridBagConstraints.FIRST_LINE_START;
        buttonGbc.insets = new Insets(25, 25, 0, 0); // space between window

//        scrollPane.setPreferredSize(new Dimension(400, 200));

        this.add(backButton, buttonGbc);
        this.add(scrollPane, panelGbc);
    }
}
