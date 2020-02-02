package task0.gui;

import gui.GUI;
import gui.GUIEvents;
import gui.controlpanel.ControlPanel;
import task0.simulator.ProjectileThrow;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ProjectileThrowGUI extends GUI {

    private final static int INITIAL_WIDTH = 1000;
    private final static int INITIAL_HEIGHT = 600;
    private final ProjectileThrowDrawBoard drawBoard = new ProjectileThrowDrawBoard();
    private final ControlPanel<ProjectileThrow> controlPanel = new ProjectileThrowControlPanel(this);

    public ProjectileThrowGUI() {
        super("Rzut ukośny");
        setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        setMinimumSize(new Dimension(800, 400));
        addComponents();
        pack();
    }

    private void addComponents() {
        add(drawBoard);
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case GUIEvents.START_SIMULATION:
                startProjectileThrowSimulation();
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                drawBoard.clearSimulation();
                break;
        }
    }

    private void startProjectileThrowSimulation() {
        drawBoard.prepareSimulation(controlPanel.prepareSimulationComponent());
        drawBoard.runSimulation();
    }
}
