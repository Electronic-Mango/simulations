package task1.gui.drawboard;

import gui.drawboard.DrawBoard;
import gui.drawboard.GridComponent;
import task1.simulator.DoublePendulum;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class DoublePendulumDrawBoard extends DrawBoard<DoublePendulum> {

    public DoublePendulumDrawBoard() {
        super(new DoublePendulumPainter());
        setBackground(Color.WHITE);
        repaint();
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Point centerPoint = new Point(getWidth() / 2, getHeight() / 2);
        Graphics2D graphics2D = prepareGraphics2D(graphics, centerPoint);
        final AffineTransform transformBeforePendulums = graphics2D.getTransform();
        simulators.forEach(simulator -> simulationPainter.paintSimulation(simulator, graphics2D));
        graphics2D.setTransform(transformBeforePendulums);
        drawOutline(graphics2D, centerPoint);
    }

    private Graphics2D prepareGraphics2D(final Graphics graphics, final Point centerPoint) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate(centerPoint.x, centerPoint.y);
        drawGrid(graphics2D, centerPoint);
        drawCenterLines(graphics2D, centerPoint);
        drawOutline(graphics2D, centerPoint);
        return graphics2D;
    }

    private void drawGrid(final Graphics2D graphics2D, final Point centerPoint) {
        GridComponent.drawGrid(graphics2D, centerPoint.x, centerPoint.y);
        graphics2D.scale(-1, 1);
        GridComponent.drawGrid(graphics2D, centerPoint.x, centerPoint.y);
        graphics2D.scale(1, -1);
        GridComponent.drawGrid(graphics2D, centerPoint.x, centerPoint.y);
        graphics2D.scale(-1, 1);
        GridComponent.drawGrid(graphics2D, centerPoint.x, centerPoint.y);
        graphics2D.scale(1, -1);
    }

    private void drawCenterLines(final Graphics2D graphics2D, final Point centerPoint) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.drawLine(-centerPoint.x, 0, centerPoint.x, 0);
        graphics2D.drawLine(0, -centerPoint.y, 0, centerPoint.y);
    }

    private void drawOutline(final Graphics2D graphics2D, final Point centerPoint) {
        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRect(-centerPoint.x, -centerPoint.y, 2 * centerPoint.x, 2 * centerPoint.y);
    }

}
