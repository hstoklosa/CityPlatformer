package game.ui;

import game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ControlsPanel extends JPanel {

    public ControlsPanel(Game game, JPanel startPanel) {
        super(new GridBagLayout());

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));

        JPanel controls = new JPanel(new GridLayout(0, 2));

        controls.setBackground(Color.WHITE);
        Font font = Game.font.deriveFont(14f);
        Border gap = BorderFactory.createEmptyBorder(0, 15, 10, 0);
        Border gap2 = BorderFactory.createEmptyBorder(0, 0, 10, 15);

        // escape key
        JPanel escContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        escContainer.setBackground(Color.WHITE);
        escContainer.setBorder(gap2);
        JLabel eKey = new JLabel(Game.createScaledImage("data/images/controls/e.png", 32));
        JLabel cKey = new JLabel(Game.createScaledImage("data/images/controls/c.png", 32));
        escContainer.add(eKey);
        escContainer.add(new JLabel(Game.createScaledImage("data/images/controls/s.png", 32)));
        escContainer.add(cKey);

        JLabel pauser = new JLabel("PAUSE");
        pauser.setBorder(gap);
        pauser.setFont(font);

        controls.add(escContainer);
        controls.add(pauser);

        // R button
        JPanel rContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rContainer.setBackground(Color.WHITE);
        rContainer.setBorder(gap2);
        JLabel rKey = new JLabel(Game.createScaledImage("data/images/controls/r.png", 32));
        rContainer.add(rKey);

        JLabel restarter = new JLabel("RESTART");
        restarter.setBorder(gap);
        restarter.setFont(font);

        controls.add(rContainer);
        controls.add(restarter);

        // S, L buttons
        JPanel saverButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saverButtons.setBackground(Color.WHITE);
        JLabel sKey = new JLabel(Game.createScaledImage("data/images/controls/s.png", 32));
        JLabel lKey = new JLabel(Game.createScaledImage("data/images/controls/l.png", 32));
        saverButtons.add(sKey);
        saverButtons.add(lKey);
        saverButtons.setBorder(gap2);

        JLabel saver = new JLabel("SAVE/LOAD");
        saver.setFont(font);
        saver.setBorder(gap);

        controls.add(saverButtons);
        controls.add(saver);

        // A, D buttons
        JPanel moveButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        moveButtons.setBackground(Color.WHITE);
        moveButtons.setBorder(gap2);

        JLabel aKey = new JLabel(Game.createScaledImage("data/images/controls/a.png", 32));
        JLabel dKey = new JLabel(Game.createScaledImage("data/images/controls/d.png", 32));
        moveButtons.add(aKey);
        moveButtons.add(dKey);

        JLabel mover = new JLabel("LEFT/RIGHT");
        mover.setFont(font);
        mover.setBorder(gap);

        controls.add(moveButtons);
        controls.add(mover);

        // Space button
        JPanel spaceContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        spaceContainer.setBackground(Color.WHITE);
        spaceContainer.setBorder(gap2);

        JLabel spaceIcon = new JLabel(Game.createScaledImage("data/images/controls/space.png", 32));
        spaceContainer.add(spaceIcon);

        JLabel jumper = new JLabel("JUMP");
        jumper.setFont(font);
        jumper.setBorder(gap);

        controls.add(spaceContainer);
        controls.add(jumper);

        // Control button
        JPanel ctrlContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ctrlContainer.setBackground(Color.WHITE);
        ctrlContainer.setBorder(gap2);

        JLabel ctrlIcon = new JLabel(Game.createScaledImage("data/images/controls/ctrl.png", 32));
        ctrlContainer.add(ctrlIcon);

        JLabel sprinter = new JLabel("SPRINT");
        sprinter.setFont(font);
        sprinter.setBorder(gap);

        controls.add(ctrlContainer);
        controls.add(sprinter);

        // Left mouse button
        JPanel lmbContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lmbContainer.setBackground(Color.WHITE);
        JLabel lmbIcon = new JLabel(Game.createScaledImage("data/images/controls/lmb.png", 32));
        lmbContainer.add(lmbIcon);
        lmbContainer.setBorder(gap2);

        JLabel shooter = new JLabel("SHOOT SHORT RANGE (SLOW)");
        shooter.setFont(font);
        shooter.setBorder(gap);

        controls.add(lmbContainer);
        controls.add(shooter);

        // right mouse button
        JPanel rmbContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rmbContainer.setBackground(Color.WHITE);
        JLabel rmbIcon = new JLabel(Game.createScaledImage("data/images/controls/rmb.png", 32));
        rmbContainer.add(rmbIcon);
        rmbContainer.setBorder(gap2);

        JLabel shooter2 = new JLabel("SHOOT LONG RANGE (QUICK)");
        shooter2.setFont(font);
        shooter2.setBorder(gap);

        controls.add(rmbContainer);
        controls.add(shooter2);



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
        this.add(controls, panelGbc);

    }
}
