package utils;

import java.util.Optional;

public class Numbers {

    public static final double TWO_PI = Math.PI * 2;

    public static Optional<Integer> tryToParse(final String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

}
