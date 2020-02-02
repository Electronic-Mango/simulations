package gui.controlpanel;

import gui.drawboard.SimulationComponent;
import simulator.RunnableSimulator;

import javax.swing.*;

public abstract class ControlPanel<T extends RunnableSimulator> extends JPanel {

    public abstract SimulationComponent<T> prepareSimulationComponent();

    protected abstract void addComponents();

}
