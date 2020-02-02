package utils;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ColorPicker {
    private static final List<Color> COLORS = Arrays.asList(
            Color.BLUE, Color.BLACK, Color.MAGENTA,
            Color.RED, Color.GREEN, Color.GRAY,
            Color.CYAN, Color.ORANGE, Color.PINK);
    private static Iterator<Color> ITERATOR = COLORS.iterator();

    public static Color getColor() {
        if (!ITERATOR.hasNext()) {
            ITERATOR = COLORS.iterator();
        }
        return ITERATOR.next();
    }
}
