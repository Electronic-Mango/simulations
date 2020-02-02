package simulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class RunnableSimulator implements Runnable {

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

    public List<Point> getListOfCoordinates() {
        return listOfCoordinates;
    }

    @Override
    public abstract void run();

}
