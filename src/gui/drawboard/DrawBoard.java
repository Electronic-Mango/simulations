package gui.drawboard;

import simulator.Simulator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

abstract public class DrawBoard<T extends Simulator> extends JPanel {

    protected final SimulationPainter<T> simulationPainter;
    protected List<T> simulators = new ArrayList<>();

    public DrawBoard(final SimulationPainter<T> simulationPainter) {
        this.simulationPainter = simulationPainter;
    }

    public void setSimulators(final List<T> simulators) {
        this.simulators = simulators;
    }

}
