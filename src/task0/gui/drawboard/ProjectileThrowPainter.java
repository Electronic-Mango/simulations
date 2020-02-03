package task0.gui.drawboard;

import gui.drawboard.SimulationPainter;
import gui.drawboard.TrajectoryPainter;
import task0.simulator.ProjectileThrow;
import utils.ColorPicker;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectileThrowPainter implements SimulationPainter<ProjectileThrow> {

    private final Map<ProjectileThrow, Color> colorMap = new HashMap<>();

    @Override
    public void paintSimulation(final ProjectileThrow simulator, final Graphics2D graphics2D) {
        graphics2D.setColor(getColorForSimulation(simulator));
        final List<Point> curvePoints = simulator.getCopyOfCoordinates();
        final Color color = getColorForSimulation(simulator);
        TrajectoryPainter.paintTrajectory(curvePoints, color, graphics2D);
    }

    private Color getColorForSimulation(final ProjectileThrow simulator) {
        if (colorMap.containsKey(simulator)) {
            return colorMap.get(simulator);
        }
        final Color color = ColorPicker.getColor();
        colorMap.put(simulator, color);
        return color;
    }

}
