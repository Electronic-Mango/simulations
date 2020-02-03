package gui;

import gui.controlpanel.ControlPanel;
import gui.drawboard.DrawBoard;
import simulator.Simulator;
import utils.ActionChangeListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

abstract public class GUI<T extends Simulator> extends JFrame implements ActionChangeListener {

    private static final Executor EXECUTOR = Executors.newCachedThreadPool();
    private static final int REFRESH_DELAY_MS = Math.round(TimeUnit.SECONDS.toMillis(1) / 60f);
    protected final List<T> simulators = new ArrayList<>();
    protected final List<CompletableFuture<Void>> simulationFutures = new ArrayList<>();
    protected final DrawBoard<T> drawBoard;
    protected final ControlPanel<T> controlPanel;
    protected final Timer timer = new Timer(REFRESH_DELAY_MS, this);

    public GUI(final String title, final DrawBoard<T> drawBoard, final ControlPanel<T> controlPanel) {
        super(title);
        this.drawBoard = drawBoard;
        this.controlPanel = controlPanel;
        configureComponents();
        addComponents();
        setLookAndFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    // Final so GUI implementations use additionalActions(final ActionEvent event) method instead.
    // This way if support for new actions is added timer action is never omitted.
    @Override
    public final void actionPerformed(final ActionEvent event) {
        if (event.getSource() == timer && areSimulationsComplete()) {
            timer.stop();
        }
        additionalActions(event);
        repaint();
    }

    @Override
    public void stateChanged(final ChangeEvent e) {
        // Adding default implementation so GUI implementations
        // without the need for ChangeListener won't have to create their own empty implementations.
    }

    public void prepareSimulation() {
        final T simulator = controlPanel.prepareSimulator();
        simulators.add(simulator);
    }

    public void startSimulation() {
        if (!timer.isRunning()) {
            timer.start();
        }
        final T simulator = simulators.stream().reduce(((first, second) -> second)).orElseThrow();
        simulationFutures.add(CompletableFuture.runAsync(simulator, EXECUTOR));
    }

    public void stopSimulation() {
        simulators.forEach(T::stop);
        simulationFutures.forEach(CompletableFuture::join);
        repaint();
        drawBoard.repaint();
    }

    abstract protected void clearSimulation();

    // To be used instead of overriding actionPerformed(final ActionEvent event).
    abstract protected void additionalActions(final ActionEvent event);

    private void configureComponents() {
        drawBoard.setSimulators(simulators);
        controlPanel.setListener(this);
        timer.setActionCommand(GUIEvents.TIMER);
    }

    private void addComponents() {
        add(drawBoard);
        add(controlPanel, BorderLayout.SOUTH);
    }

    protected void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private boolean areSimulationsComplete() {
        return simulationFutures.stream().allMatch(CompletableFuture::isDone);
    }

}
