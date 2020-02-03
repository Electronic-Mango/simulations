package utils;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.awt.Color.*;

public class ColorPicker {

    private static final List<Color> COLORS = Arrays.asList(BLUE, BLACK, MAGENTA, RED, GREEN, GRAY, CYAN, ORANGE, PINK);
    private static Iterator<Color> ITERATOR = COLORS.iterator();

    public static Color getColor() {
        if (!ITERATOR.hasNext()) {
            ITERATOR = COLORS.iterator();
        }
        return ITERATOR.next();
    }

}
