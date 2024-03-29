package doublependulum.simulator;

import java.awt.*;

import static utils.Numbers.TWO_PI;

public class Pendulum {

    private final Point bob = new Point();
    private double velocity = 0;
    private int mass;
    private int length;
    private double angle;
    private double friction;

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
