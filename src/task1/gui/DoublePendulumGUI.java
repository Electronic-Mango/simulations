package task1.gui;

import gui.GUI;
import gui.GUIEvents;
import task1.gui.drawboard.DoublePendulumDrawBoard;
import task1.simulator.DoublePendulum;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DoublePendulumGUI extends GUI<DoublePendulum> {

    private static final int MIN_WIDTH = 850;
    private static final int MIN_HEIGHT = 500;
    private static final int INITIAL_WIDTH = 850;
    private static final int INITIAL_HEIGHT = 800;
    private final DoublePendulumDrawBoard drawBoard = (DoublePendulumDrawBoard) super.drawBoard;
    private final DoublePendulumControlPanel controlPanel = (DoublePendulumControlPanel) super.controlPanel;

    public DoublePendulumGUI() {
        super("Podwójne wahadło", new DoublePendulumDrawBoard(), new DoublePendulumControlPanel());
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        pack();
        prepareSimulation();
        repaint();
    }

    @Override
    public void clearSimulation() {
        simulators.forEach((DoublePendulum::resetSavedCoordinates));
    }

    @Override
    public void additionalActions(final ActionEvent event) {
        switch (event.getActionCommand()) {
            case GUIEvents.START_SIMULATION:
                clearSimulation();
                startSimulation();
                controlPanel.startSimulation();
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                clearSimulation();
                break;
            case GUIEvents.STOP_SIMULATION:
                stopSimulation();
                controlPanel.stopSimulation();
                controlPanel.updateAngleSliders(simulators.stream().findFirst().orElseThrow());
                break;
        }
    }

    @Override
    public void stateChanged(final ChangeEvent event) {
        if (!timer.isRunning()) {
            clearSimulation();
        }
        final DoublePendulum doublePendulum = simulators.stream().findFirst().orElseThrow();
        doublePendulum.modifyPendulum(controlPanel.prepareSimulator());
        drawBoard.repaint();
    }

}
