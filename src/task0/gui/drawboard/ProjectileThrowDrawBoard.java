package task0.gui.drawboard;

import gui.drawboard.DrawBoard;
import gui.drawboard.GridComponent;
import task0.simulator.ProjectileThrow;

import java.awt.*;

public class ProjectileThrowDrawBoard extends DrawBoard<ProjectileThrow> {

    public ProjectileThrowDrawBoard() {
        super(new ProjectileThrowPainter());
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = prepareGraphics2D(graphics);
        simulators.forEach(simulator -> simulationPainter.paintSimulation(simulator, graphics2D));
        drawOutline(graphics2D);
    }

    private Graphics2D prepareGraphics2D(final Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate(0, getHeight());
        graphics2D.scale(1, -1);
        GridComponent.drawGrid(graphics, getWidth(), getHeight());
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return graphics2D;
    }

    private void drawOutline(final Graphics2D graphics2D) {
        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRect(0, 1, getWidth() - 1, getHeight() - 1);
    }

}
