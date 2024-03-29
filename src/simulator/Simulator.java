package simulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Simulator implements Runnable {

    protected final List<Point> listOfCoordinates = new ArrayList<>();
    protected boolean run = false;

    public void stop() {
        run = false;
    }

    protected void waitBetweenCalculations() {
        try {
            Thread.sleep(getDelayBetweenCalculationsMs());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    abstract protected int getDelayBetweenCalculationsMs();

    public List<Point> getCopyOfCoordinates() {
        return List.copyOf(listOfCoordinates);
    }

}
