package task1.simulator;

import java.awt.*;

public class Pendulum {

    private static final double TWO_PI = 2 * Math.PI;
    private final Point bob = new Point();
    private int mass = 50;
    private int length = 80;
    private double angle = Math.toRadians(90);
    private double velocity = 0;
    private double friction = 1;

    public Pendulum() {

    }

    public Pendulum(int mass, int length, double angle, double friction) {
        this.mass = mass;
        this.length = length;
        this.angle = angle;
        this.friction = 1 - (friction / 100d);
    }

    public void calculateBobPosition() {
        bob.move((int) (Math.sin(angle) * length), (int) (Math.cos(angle) * length));
    }

    public void resetVelocity() {
        velocity = 0;
    }

    public void increaseAngle() {
        angle = ((angle + velocity) % TWO_PI);
    }

    public void increaseVelocity(final double angleAcceleration) {
        velocity += angleAcceleration;
        velocity *= friction;
    }

    public void modifyPendulum(final Pendulum pendulum, final boolean modifyAngle) {
        this.mass = pendulum.mass;
        this.length = pendulum.length;
        this.friction = pendulum.friction;
        if (modifyAngle) {
            this.angle = pendulum.angle;
        }
    }

    public Point getBob() {
        return bob;
    }

    public int getMass() {
        return mass;
    }

    public int getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }

    public double getVelocity() {
        return velocity;
    }
}
