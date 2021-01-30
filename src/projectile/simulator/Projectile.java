package projectile.simulator;

import java.awt.geom.Point2D;

public class Projectile {

    private static final double G = 100;
    private final Point2D coordinates = new Point2D.Double();
    private final double initialSpeed;
    private final double angle;
    private final double friction;

    Projectile(final double initialSpeed, final double angleDegrees, final double friction) {
        this.initialSpeed = initialSpeed;
        this.friction = friction;
        this.angle = Math.toRadians(angleDegrees);
        if (angleDegrees > 80 || angleDegrees < 10) {
            throw new IllegalArgumentException("Angle for projectile throw is outside of range 10-80");
        }
    }

    void calculatePosition(final double timePassed) {
        final double newX = calculateHorizontalPosition(timePassed);
        final double newY = calculateVerticalPosition(timePassed);
        coordinates.setLocation(newX, newY);
    }

    Point2D getCoordinates() {
        return coordinates;
    }

    private double calculateHorizontalPosition(final double timePassed) {
        return (initialSpeed * timePassed * Math.cos(angle)) * friction;
    }

    private double calculateVerticalPosition(final double timePassed) {
        return ((initialSpeed * timePassed * Math.sin(angle)) - ((G * timePassed * timePassed) / 2)) * friction;
    }

}
