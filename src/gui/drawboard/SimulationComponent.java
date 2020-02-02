package gui.drawboard;

import simulator.RunnableSimulator;
import utils.ColorPicker;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SimulationComponent<T extends RunnableSimulator> {

    protected static final Executor EXECUTOR = Executors.newCachedThreadPool();
    protected final T simulator;
    protected final Color color = ColorPicker.getColor();

    public SimulationComponent(T simulator) {
        this.simulator = simulator;
    }

    public CompletableFuture<Void> runSimulation() {
        return CompletableFuture.runAsync(simulator, EXECUTOR);
    }

    public void stop() {
        simulator.stop();
    }

    public void drawSimulationResults(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        final List<Point> curvePoints = new ArrayList<>(simulator.getListOfCoordinates());
        final Point firstPoint = curvePoints.stream().findFirst().orElse(new Point());
        final GeneralPath trajectory = new GeneralPath();
        trajectory.moveTo(firstPoint.x, firstPoint.y);
        curvePoints.stream().skip(1).forEach(point -> trajectory.lineTo(point.x, point.y));
        graphics2D.draw(trajectory);
    }

    public T getSimulator() {
        return simulator;
    }
}
