package gui.drawboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DrawBoard extends JPanel implements ActionListener {

    private static final int REFRESH_DELAY_MS = 17;
    protected final Timer timer = new Timer(REFRESH_DELAY_MS, this);

    protected abstract boolean isSimulationComplete();

    public abstract void clearSimulation();

    public void runSimulation() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (isSimulationComplete()) {
            timer.stop();
        }
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        GridComponent.drawGrid(graphics, getWidth(), getHeight());
    }
}
