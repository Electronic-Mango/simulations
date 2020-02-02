package task1.gui.drawboard;

import gui.drawboard.SimulationComponent;
import task1.simulator.DoublePendulum;
import task1.simulator.Pendulum;

import java.awt.*;

public class DoublePendulumComponent extends SimulationComponent<DoublePendulum> {

    public DoublePendulumComponent(final DoublePendulum doublePendulum) {
        super(doublePendulum);
    }

    public void modifyPendulum(DoublePendulum doublePendulum) {
        simulator.modifyPendulum(doublePendulum);
    }

    @Override
    public void drawSimulationResults(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.translate(450, 300);
        super.drawSimulationResults(graphics2D);
        drawDoublePendulum(graphics2D);
    }

    private void drawDoublePendulum(final Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(Color.BLACK);
        final Pendulum pendulum1 = simulator.getPendulum1();
        final Pendulum pendulum2 = simulator.getPendulum2();
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, 0, pendulum1.getBob().x, pendulum1.getBob().y);
        graphics2D.translate(pendulum1.getBob().x, pendulum1.getBob().y);
        graphics2D.drawLine(0, 0, pendulum2.getBob().x, pendulum2.getBob().y);
        graphics2D.setColor(Color.RED);
        final int bob1Size = 20 + (pendulum1.getMass() / 10);
        graphics2D.fillOval(-(bob1Size / 2), -(bob1Size / 2), bob1Size, bob1Size);
        graphics2D.translate(pendulum2.getBob().x, pendulum2.getBob().y);
        final int bob2Size = 20 + (pendulum2.getMass() / 10);
        graphics2D.fillOval(-(bob2Size / 2), -(bob2Size / 2), bob2Size, bob2Size);
    }
}
