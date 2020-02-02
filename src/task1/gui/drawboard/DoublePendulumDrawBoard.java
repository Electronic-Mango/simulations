package task1.gui.drawboard;

import gui.drawboard.DrawBoard;
import task1.simulator.DoublePendulum;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class DoublePendulumDrawBoard extends DrawBoard {

    private DoublePendulumComponent doublePendulumComponent = new DoublePendulumComponent(new DoublePendulum());
    private CompletableFuture<Void> doublePendulumFuture = new CompletableFuture<>();

    public DoublePendulumDrawBoard() {
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.WHITE);
        repaint();
    }

    public void stopSimulation() {
        doublePendulumComponent.stop();
        doublePendulumFuture.join();
    }

    public void modifyPendulum(final DoublePendulum doublePendulum) {
        doublePendulumComponent.modifyPendulum(doublePendulum);
    }

    public DoublePendulum getDoublePendulum() {
        return doublePendulumComponent.getSimulator();
    }

    @Override
    public void runSimulation() {
        super.runSimulation();
        doublePendulumComponent.getSimulator().getListOfCoordinates().clear();
        doublePendulumFuture = doublePendulumComponent.runSimulation();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.drawLine(0, 300, 1000, 300);
        graphics2D.drawLine(450, 0, 450, 800);
        doublePendulumComponent.drawSimulationResults(graphics2D);
    }

    @Override
    protected boolean isSimulationComplete() {
        return doublePendulumFuture.isDone();
    }

    @Override
    public void clearSimulation() {
        doublePendulumComponent.getSimulator().resetSavedCoordinates();
        repaint();
    }
}
