package task1.gui.drawboard;

import gui.drawboard.SimulationPainter;
import gui.drawboard.TrajectoryPainter;
import task1.simulator.DoublePendulum;

import java.awt.*;
import java.util.List;

public class DoublePendulumPainter implements SimulationPainter<DoublePendulum> {

    @Override
    public void paintSimulation(final DoublePendulum simulator, final Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final List<Point> curvePoints = simulator.getCopyOfCoordinates();
        TrajectoryPainter.paintTrajectory(curvePoints, Color.BLUE, graphics2D);
        drawDoublePendulum(simulator, graphics2D);
    }

    private void drawDoublePendulum(final DoublePendulum simulator, final Graphics2D graphics2D) {
        final Point bob1 = simulator.getPendulum1().getBob();
        final Point bob2 = simulator.getPendulum2().getBob();
        final int bob1Size = 10 + (simulator.getPendulum1().getMass() * 2);
        final int bob2Size = 10 + (simulator.getPendulum2().getMass() * 2);
        graphics2D.setStroke(new BasicStroke(3));
        drawArms(bob1, bob2, graphics2D);
        drawBobs(bob2, bob1Size, bob2Size, graphics2D);
    }

    private void drawArms(final Point bob1, final Point bob2, final Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, 0, bob1.x, bob1.y);
        graphics2D.translate(bob1.x, bob1.y);
        graphics2D.drawLine(0, 0, bob2.x, bob2.y);
    }

    private void drawBobs(final Point bob2, final int bob1Size, final int bob2Size, final Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        graphics2D.fillOval(-(bob1Size / 2), -(bob1Size / 2), bob1Size, bob1Size);
        graphics2D.translate(bob2.x, bob2.y);
        graphics2D.fillOval(-(bob2Size / 2), -(bob2Size / 2), bob2Size, bob2Size);
    }
}
