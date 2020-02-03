package task0.gui;

import gui.GUI;
import gui.GUIEvents;
import task0.gui.drawboard.ProjectileThrowDrawBoard;
import task0.simulator.ProjectileThrow;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ProjectileThrowGUI extends GUI<ProjectileThrow> {

    private final static int INITIAL_WIDTH = 1000;
    private final static int INITIAL_HEIGHT = 600;
    private final static int MIN_WIDTH = 800;
    private final static int MIN_HEIGHT = 400;

    public ProjectileThrowGUI() {
        super("Rzut ukośny", new ProjectileThrowDrawBoard(), new ProjectileThrowControlPanel());
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
            case GUIEvents.START_SIMULATION:
                prepareSimulation();
                startSimulation();
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                stopSimulation();
                clearSimulation();
                break;
        }
    }

}
