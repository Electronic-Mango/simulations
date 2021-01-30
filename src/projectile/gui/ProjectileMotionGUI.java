package projectile.gui;

import gui.GUI;
import gui.GUIEvents;
import projectile.gui.drawboard.ProjectileMotionDrawBoard;
import projectile.simulator.ProjectileMotion;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ProjectileMotionGUI extends GUI<ProjectileMotion> {

    private final static int INITIAL_WIDTH = 1000;
    private final static int INITIAL_HEIGHT = 600;
    private final static int MIN_WIDTH = 800;
    private final static int MIN_HEIGHT = 400;

    public ProjectileMotionGUI() {
        super("Projectile motion", new ProjectileMotionDrawBoard(), new ProjectileMotionControlPanel());
        setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        pack();
    }

    @Override
    public void clearSimulation() {
        simulators.clear();
        simulationFutures.clear();
        repaint();
    }

    @Override
    public void additionalActions(final ActionEvent event) {
        switch (event.getActionCommand()) {
            case GUIEvents.START_SIMULATION -> {
                prepareSimulation();
                startSimulation();
            }
            case GUIEvents.CLEAR_SIMULATIONS -> {
                stopSimulation();
                clearSimulation();
            }
        }
    }

}
