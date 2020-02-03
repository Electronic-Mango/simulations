package gui.drawboard;

import java.awt.*;

public class GridComponent {

    public final static int MAJOR_GRID_SPACING = 50;
    public final static int MINOR_GRID_SPACING = 10;
    private final static Color MAJOR_GRID_COLOR = Color.LIGHT_GRAY;
    private final static Color MINOR_GRID_COLOR = Color.getHSBColor(0, 0, 0.95f);

    public static void drawGrid(final Graphics graphics, final int width, final int height) {
        drawMinorGrid(graphics, width, height);
        drawMajorGrid(graphics, width, height);
        graphics.drawRect(0, 0, width - 1, height - 1);
    }

    private static void drawMinorGrid(final Graphics graphics, final int width, final int height) {
        drawGrid(graphics, width, height, MINOR_GRID_COLOR, MINOR_GRID_SPACING);
    }

    private static void drawMajorGrid(final Graphics graphics, final int width, final int height) {
        drawGrid(graphics, width, height, MAJOR_GRID_COLOR, MAJOR_GRID_SPACING);
    }

    private static void drawGrid(final Graphics graphics, final int width, final int height,
                                 final Color color, final int spacing) {
        graphics.setColor(color);
        for (int i = 0; i <= width; i += spacing) {
            graphics.drawLine(i, 0, i, height);
        }
        for (int i = 0; i <= width; i += spacing) {
            graphics.drawLine(0, i, width, i);
        }
    }

}
