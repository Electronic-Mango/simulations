package utils;

import java.util.concurrent.TimeUnit;

public class StopWatch {

    public static final double NANOSECONDS_IN_SECOND = TimeUnit.SECONDS.toNanos(1);
    private long startTime = 0;
    private long stopTime = 0;
    private boolean isRunning = false;

    public static StopWatch createStarted() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        return stopWatch;
    }

    public void start() {
        isRunning = true;
        startTime = System.nanoTime();
        stopTime = startTime;
    }

    public void stop() {
        isRunning = false;
        stopTime = System.nanoTime();
    }

    public double getTime() {
        if (isRunning) {
            return nanoToSeconds(System.nanoTime() - startTime);
        } else {
            return nanoToSeconds(stopTime - startTime);
        }
    }

    private double nanoToSeconds(final long nanoseconds) {
        return nanoseconds / NANOSECONDS_IN_SECOND;
    }

}
