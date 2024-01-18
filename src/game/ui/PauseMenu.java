package game.ui;

import game.Game;
import game.levels.Level;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class PauseMenu {
    private JPanel pausePanel;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JSlider volumeSlider;
    private JButton closeButton;
    private JButton muteButton;
    private JPanel volumeSettings;

    public PauseMenu(Game g) {
        pausePanel.setBackground(Color.WHITE);
        pausePanel.setVisible(false);
        pausePanel.setSize(400, 400);
        pausePanel.setBounds(0, 0, 400, 400);

        Font buttonFont = Game.font.deriveFont(10f);
        Insets buttonPadding = new Insets(10, 10, 10, 10);

        Border buttonMargin = BorderFactory.createEmptyBorder(0, 0, 10, 0);

        resumeButton.setFont(buttonFont);
        resumeButton.setBackground(Color.WHITE);
        resumeButton.setMargin(buttonPadding);
        resumeButton.setBorder(BorderFactory.createCompoundBorder(buttonMargin, resumeButton.getBorder()));

        mainMenuButton.setFont(buttonFont);
        mainMenuButton.setBackground(Color.WHITE);
        mainMenuButton.setMargin(buttonPadding);
        mainMenuButton.setBorder(BorderFactory.createCompoundBorder(buttonMargin, mainMenuButton.getBorder()));

        exitButton.setFont(buttonFont);
        exitButton.setBackground(Color.WHITE);
        exitButton.setMargin(buttonPadding);
        exitButton.setBorder(BorderFactory.createCompoundBorder(buttonMargin, exitButton.getBorder()));

        closeButton.setIcon(Game.createScaledImage("data/images/controls/close.png", 36));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);

        closeButton.addActionListener(e -> {
            g.getLevel().start();
            pausePanel.setVisible(false);
        });

        resumeButton.addActionListener(e -> {
            g.restartGame();
        });

        mainMenuButton.addActionListener(e -> {
            g.getLevel().stop();
            g.switchPanel(g.getStartMenu().getMainPanel(), g.getLayeredPanel());
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        volumeSettings.setBackground(Color.WHITE);

        muteButton.setIcon(Game.createScaledImage("data/images/controls/volume.png", 36));
        muteButton.setBorderPainted(false);
        muteButton.setFocusPainted(false);
        muteButton.setContentAreaFilled(false);

        volumeSlider.setBackground(Color.WHITE);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);

        // Add a ChangeListener to the JSlider to adjust the volume
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    System.out.println(source.getValue());
                    Level.BACKGROUND.setVolume((double) source.getValue() / 3);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return pausePanel;
    }
}
