package gui.drawboard;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class TrajectoryPainter {

    private static final int NUMBER_OF_POINTS_REQUIRED_FOR_TRAJECTORY = 2;
    private static final int POINTS_USED_FOR_MOVE = 1;

    public static void paintTrajectory(final List<Point> curvePoints, final Color color, final Graphics2D graphics2D) {
        if (curvePoints.size() < NUMBER_OF_POINTS_REQUIRED_FOR_TRAJECTORY) return;
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(1));
        final Point firstPoint = curvePoints.stream().findFirst().orElseThrow();
        final GeneralPath trajectory = new GeneralPath();
        trajectory.moveTo(firstPoint.x, firstPoint.y);
        curvePoints.stream().skip(POINTS_USED_FOR_MOVE).forEach(point -> trajectory.lineTo(point.x, point.y));
        graphics2D.draw(trajectory);
    }

}
