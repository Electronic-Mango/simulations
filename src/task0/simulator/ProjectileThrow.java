package task0.simulator;

import simulator.RunnableSimulator;
import utils.StopWatch;

import java.awt.*;
import java.awt.geom.Point2D;

public class ProjectileThrow extends RunnableSimulator {

    protected static final int DELAY_BETWEEN_CALCULATIONS_MS = 1;
    private final Projectile projectile;

    public ProjectileThrow(final double initialSpeed, final double angleDegrees, final double friction) {
        projectile = new Projectile(initialSpeed, angleDegrees, friction);
    }

    @Override
    protected int getDelayBetweenCalculationsMs() {
        return DELAY_BETWEEN_CALCULATIONS_MS;
    }

    @Override
    public void run() {
        run = true;
        final StopWatch stopWatch = StopWatch.createStarted();
        do {
            calculatePosition(stopWatch.getTime());
            addNewCoordinateToList();
            waitBetweenCalculations();
        } while (projectile.getCoordinates().getY() > 0 && run);
        stopWatch.stop();
        run = false;
    }

    private void calculatePosition(final double passedTime) {
        projectile.calculatePosition(passedTime);
    }

    private void addNewCoordinateToList() {
        final Point newCoordinate = createNewPoint(projectile.getCoordinates());
        if (!listOfCoordinates.contains(newCoordinate)) {
            listOfCoordinates.add(createNewPoint(newCoordinate));
        }
    }

    private Point createNewPoint(Point2D coordinates) {
        return new Point((int) Math.round(coordinates.getX()), (int) Math.round(coordinates.getY()));
    }
}
