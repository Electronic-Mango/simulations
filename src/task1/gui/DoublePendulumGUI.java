package task1.gui;

import gui.GUI;
import gui.GUIEvents;
import task1.gui.drawboard.DoublePendulumDrawBoard;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DoublePendulumGUI extends GUI {

    private final DoublePendulumDrawBoard drawBoard = new DoublePendulumDrawBoard();
    private final DoublePendulumControlPanel controlPanel = new DoublePendulumControlPanel(this, this);

    public DoublePendulumGUI() {
        super("Podwójne wahadło");
        setResizable(false);
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
                drawBoard.modifyPendulum(controlPanel.getNewPendulum());
                drawBoard.runSimulation();
                controlPanel.startSimulation();
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                drawBoard.clearSimulation();
                break;
            case GUIEvents.STOP_SIMULATION:
                drawBoard.stopSimulation();
                controlPanel.stopSimulation();
                controlPanel.updateAngleSliders(drawBoard.getDoublePendulum());
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        if (controlPanel == null) {
            return;
        }
        drawBoard.modifyPendulum(controlPanel.getNewPendulum());
        drawBoard.repaint();
    }
}
