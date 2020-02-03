package gui.drawboard;

import java.awt.*;

public interface SimulationPainter<T> {

    void paintSimulation(final T simulator, final Graphics2D graphics2D);

}
