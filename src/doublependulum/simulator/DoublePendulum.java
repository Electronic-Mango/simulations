package doublependulum.simulator;

import simulator.Simulator;

import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DoublePendulum extends Simulator {

    private static final double G = 0.4;
    private static final int DELAY_BETWEEN_CALCULATIONS = 10;
    private final Pendulum pendulum1;
    private final Pendulum pendulum2;

    public DoublePendulum(final Pendulum pendulum1, final Pendulum pendulum2) {
        this.pendulum1 = pendulum1;
        this.pendulum2 = pendulum2;
        calculateBobsPositions();
    }

    public void modifyPendulum(final DoublePendulum doublePendulum) {
        this.pendulum1.modifyPendulum(doublePendulum.pendulum1, !run);
        this.pendulum2.modifyPendulum(doublePendulum.pendulum2, !run);
        calculateBobsPositions();
    }

    @Override
    protected int getDelayBetweenCalculationsMs() {
        return DELAY_BETWEEN_CALCULATIONS;
    }

    @Override
    public void run() {
        run = true;
        while (run) {
            increaseAngles();
            calculateBobsPositions();
            addNewCoordinateToList();
            waitBetweenCalculations();
        }
        resetVelocity();
        run = false;
    }

    private void addNewCoordinateToList() {
        final Point newCoordinate = createNewPoint(pendulum2.getBob());
        newCoordinate.translate(pendulum1.getBob().x, pendulum1.getBob().y);
        if (!listOfCoordinates.contains(newCoordinate)) {
            listOfCoordinates.add(createNewPoint(newCoordinate));
        }
    }

    private Point createNewPoint(final Point2D coordinates) {
        return new Point((int) Math.round(coordinates.getX()), (int) Math.round(coordinates.getY()));
    }

    public Pendulum getPendulum1() {
        return pendulum1;
    }

    public Pendulum getPendulum2() {
        return pendulum2;
    }

    public void resetSavedCoordinates() {
        listOfCoordinates.clear();
    }

    private void resetVelocity() {
        pendulum1.resetVelocity();
        pendulum2.resetVelocity();
    }

    private void calculateBobsPositions() {
        pendulum1.calculateBobPosition();
        pendulum2.calculateBobPosition();
    }

    private void increaseAngles() {
        increaseVelocity();
        pendulum1.increaseAngle();
        pendulum2.increaseAngle();
    }

    private void increaseVelocity() {
        pendulum1.increaseVelocity(calculateAngle1Acceleration());
        pendulum2.increaseVelocity(calculateAngle2Acceleration());
    }

    private double calculateAngle1Acceleration() {
        // These lines are way too long, however I find it more readable for complicated mathematical equations.
        final double num1 = -G * (2 * pendulum1.getMass() + pendulum2.getMass()) * sin(pendulum1.getAngle());
        final double num2 = -pendulum2.getMass() * G * sin(pendulum1.getAngle() - 2 * pendulum2.getAngle());
        final double num3 = -2 * sin(pendulum1.getAngle() - pendulum2.getAngle()) * pendulum2.getMass();
        final double num4 = pendulum2.getVelocity() * pendulum2.getVelocity() * pendulum2.getLength() + pendulum1.getVelocity() * pendulum1.getVelocity() * pendulum1.getLength() * cos(pendulum1.getAngle() - pendulum2.getAngle());
        final double den = pendulum1.getLength() * (2 * pendulum1.getMass() + pendulum2.getMass() - pendulum2.getMass() * cos(2 * pendulum1.getAngle() - 2 * pendulum2.getAngle()));
        return (num1 + num2 + num3 * num4) / den;
    }

    private double calculateAngle2Acceleration() {
        // These lines are way too long, however I find it more readable for complicated mathematical equations.
        final double num1 = 2 * sin(pendulum1.getAngle() - pendulum2.getAngle());
        final double num2 = (pendulum1.getVelocity() * pendulum1.getVelocity() * pendulum1.getLength() * (pendulum1.getMass() + pendulum2.getMass()));
        final double num3 = G * (pendulum1.getMass() + pendulum2.getMass()) * cos(pendulum1.getAngle());
        final double num4 = pendulum2.getVelocity() * pendulum2.getVelocity() * pendulum2.getLength() * pendulum2.getMass() * cos(pendulum1.getAngle() - pendulum2.getAngle());
        final double den = pendulum2.getLength() * (2 * pendulum1.getMass() + pendulum2.getMass() - pendulum2.getMass() * cos(2 * pendulum1.getAngle() - 2 * pendulum2.getAngle()));
        return (num1 * (num2 + num3 + num4)) / den;
    }

}
