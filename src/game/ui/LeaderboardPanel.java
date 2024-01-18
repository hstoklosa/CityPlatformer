package game.ui;

import game.Game;
import game.GameHighScore;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class LeaderboardPanel extends JPanel {

    public LeaderboardPanel(Game game, JPanel startPanel) {
        super(new GridBagLayout());

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));


        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(Color.WHITE);
        Border labelMargin = BorderFactory.createEmptyBorder(0, 0, 20, 0);


        try {
            HashMap<String, Integer> scores = GameHighScore.readScores();
            // System.out.println(entry.getKey()+" : "+entry.getValue());

            if (scores.size() > 0) {
                int baseFontSize = 32;
                int step = 5;
                int count = 0;

                for (HashMap.Entry<String, Integer> entry : scores.entrySet()) {
                    float fontSize = baseFontSize / 2;
                    Font scoreFont = Game.font.deriveFont(fontSize);

                    if (count < 3) {
                        fontSize = baseFontSize - (step * (count));
                    }

                    JLabel score = new JLabel((count < 3 ? String.valueOf(count + 1) + "." : "") + entry.getKey() + ":" + entry.getValue());
                    score.setFont(scoreFont);
                    score.setBorder(labelMargin);

                    score.setAlignmentX(Component.CENTER_ALIGNMENT);
                    scorePanel.add(score);
                    count++;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Create a JScrollPane containing the JPanel
        JScrollPane scrollPane = new JScrollPane(scorePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));

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

        this.add(backButton, buttonGbc);
        this.add(scrollPane, panelGbc);
    }
}
