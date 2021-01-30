package gameoflife.gui;

import gameoflife.gui.controlpanel.GameOfLifeControlPanel;
import gameoflife.simulator.GameOfLife;
import gui.GUIEvents;
import utils.ActionChangeListener;
import utils.Numbers;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

public class GameOfLifeGUI extends JFrame implements ActionChangeListener, MouseListener {

    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 400;
    private static final int INITIAL_WIDTH = 600;
    private static final int INITIAL_HEIGHT = 750;
    private static final int INITIAL_ROWS = 60;
    private static final int INITIAL_COLUMNS = 60;
    private final GameOfLife gameOfLife = new GameOfLife();
    private final Timer timer = new Timer(250, this);
    private final JMenuBar menuBar = new JMenuBar();
    private final GameOfLifeControlPanel controlPanel = new GameOfLifeControlPanel(this);
    private final GameOfLifeDrawBoard drawBoard;
    private boolean mouseIsPressed = false;

    public GameOfLifeGUI() {
        super("Game of Life");
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        configureGameOfLife(INITIAL_ROWS, INITIAL_COLUMNS);
        configureMenuBar();
        drawBoard = new GameOfLifeDrawBoard(gameOfLife.getNumberOfRows(), gameOfLife.getNumberOfColumns(), this);
        configureComponents();
        addComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        displayGameState();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        switch (event.getActionCommand()) {
            case GUIEvents.TIMER:
                refreshGameState();
                break;
            case GUIEvents.RANDOM_STATE:
                setRandomGameState();
                break;
            case GUIEvents.START_SIMULATION:
                startGame();
                break;
            case GUIEvents.RESIZE_SIMULATION:
                resizeGameState();
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                if (clearGameState()) break;
            case GUIEvents.STOP_SIMULATION:
                stopGame();
                break;
        }
    }

    @Override
    public void stateChanged(final ChangeEvent event) {
        if (controlPanel == null) return;
        timer.setDelay(controlPanel.getAnimationSpeed());
        gameOfLife.setRuleSet(controlPanel.getNewRuleSet());
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        mouseIsPressed = true;
        switchCellState((JPanel) event.getSource());
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        mouseIsPressed = false;
    }

    @Override
    public void mouseEntered(final MouseEvent event) {
        if (mouseIsPressed) switchCellState((JPanel) event.getSource());
    }

    @Override
    public void mouseExited(final MouseEvent event) {
    }

    private void configureGameOfLife(final int numberOfRows, final int numberOfColumns) {
        gameOfLife.createGameOfLifeState(numberOfRows, numberOfColumns);
        gameOfLife.setRandomGameState();
    }

    private void configureMenuBar() {
        final JLabel menuText = new JLabel(" Use mouse to change cells' state");
        menuText.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        menuText.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(menuText);
        menuBar.setAlignmentX(0.5f);
    }

    private void configureComponents() {
        drawBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        timer.setActionCommand(GUIEvents.TIMER);
    }

    private void addComponents() {
        setJMenuBar(menuBar);
        add(drawBoard);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void switchCellState(final JPanel cell) {
        final Point coordinates = drawBoard.getCoordinates(cell);
        gameOfLife.getGameState().switchState(coordinates.x, coordinates.y);
        displayGameState();
    }

    private void displayGameState() {
        drawBoard.setVisible(false);
        for (int row = 0; row < gameOfLife.getNumberOfRows(); row++) {
            for (int column = 0; column < gameOfLife.getNumberOfColumns(); column++) {
                drawBoard.setCellState(gameOfLife.getGameState().getState(row, column), row, column);
            }
        }
        drawBoard.setVisible(true);
    }

    private void refreshGameState() {
        gameOfLife.calculateNewGameState();
        displayGameState();
    }

    private void setRandomGameState() {
        gameOfLife.setRandomGameState();
        displayGameState();
    }

    private void startGame() {
        timer.start();
        controlPanel.switchToStopButton();
    }

    private void resizeGameState() {
        if (changeSimulationSize() && timer.isRunning()) {
            actionPerformed(new ActionEvent(this, 0, GUIEvents.STOP_SIMULATION));
        }
    }

    private boolean changeSimulationSize() {
        final Optional<Integer> columns = getSizeFromUser("Number of columns");
        if (columns.isEmpty()) return false;
        final Optional<Integer> rows = getSizeFromUser("Number of rows");
        if (rows.isEmpty()) return false;
        prepareSimulationSize(columns.get(), rows.get());
        return true;
    }

    private Optional<Integer> getSizeFromUser(final String message) {
        final Optional<Integer> value = Numbers.tryToParse(JOptionPane.showInputDialog(this, message));
        if (value.isEmpty() || value.get() < 1) {
            JOptionPane.showMessageDialog(this, "Incorrect value!");
        }
        return value;
    }

    private void prepareSimulationSize(final int columns, final int rows) {
        configureGameOfLife(rows, columns);
        drawBoard.setCellMapSize(rows, columns);
        drawBoard.configureGameCells();
        displayGameState();
    }

    private boolean clearGameState() {
        gameOfLife.clearGameState();
        displayGameState();
        return !timer.isRunning();
    }

    private void stopGame() {
        timer.stop();
        controlPanel.switchToStartButton();
    }

}
