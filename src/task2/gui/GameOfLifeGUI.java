package task2.gui;

import gui.GUI;
import gui.GUIEvents;
import task2.gui.controlpanel.GameOfLifeControlPanel;
import task2.simulator.GameOfLifeSimulator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifeGUI extends GUI implements MouseListener {

    private static final int ROWS = 60;
    private static final int COLUMNS = 60;
    private final GameOfLifeSimulator gameOfLifeSimulator = new GameOfLifeSimulator();
    private final JMenuBar menuBar = new JMenuBar();
    private final GameOfLifeDrawBoard drawBoard;
    private final GameOfLifeControlPanel controlPanel;
    private final Timer timer = new Timer(250, this);
    private boolean mouseIsPressed = false;

    public GameOfLifeGUI() {
        super("Gra w Życie");
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 750));
        configureGameOfLife(ROWS, COLUMNS);
        configureMenuBar();
        controlPanel = new GameOfLifeControlPanel(this, this);
        drawBoard = new GameOfLifeDrawBoard(gameOfLifeSimulator.getNumberOfRows(), gameOfLifeSimulator.getNumberOfColumns(), this);
        drawBoard.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        addComponents();
        pack();
        displayGameState();
        timer.setActionCommand(GUIEvents.RUN_GAME_OF_LIFE);
    }

    private void configureMenuBar() {
        final JLabel menuText = new JLabel(" Użyj myszki do dynamicznej zmiany stanu gry");
        menuText.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        menuText.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(menuText);
        menuBar.setAlignmentX(0.5f);
    }

    private void configureGameOfLife(final int numberOfRows, final int numberOfColumns) {
        gameOfLifeSimulator.createGameOfLifeState(numberOfRows, numberOfColumns);
        gameOfLifeSimulator.setRandomGameState();
    }

    private void addComponents() {
        setJMenuBar(menuBar);
        add(drawBoard);
        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case GUIEvents.RUN_GAME_OF_LIFE:
                gameOfLifeSimulator.calculateNewGameState();
                displayGameState();
                break;
            case GUIEvents.RANDOM_STATE:
                gameOfLifeSimulator.setRandomGameState();
                displayGameState();
                break;
            case GUIEvents.START_SIMULATION:
                timer.start();
                controlPanel.switchToStopButton();
                break;
            case GUIEvents.RESIZE_SIMULATION:
                if (changeSimulationSize() && timer.isRunning()) {
                    actionPerformed(new ActionEvent(this, 0, GUIEvents.STOP_SIMULATION));
                }
                break;
            case GUIEvents.CLEAR_SIMULATIONS:
                gameOfLifeSimulator.clearGameState();
                displayGameState();
                if (!timer.isRunning()) break;
            case GUIEvents.STOP_SIMULATION:
                timer.stop();
                controlPanel.switchToStartButton();
                break;

        }
    }

    private void displayGameState() {
        drawBoard.setVisible(false);
        for (int i = 0; i < gameOfLifeSimulator.getNumberOfRows(); i++) {
            for (int j = 0; j < gameOfLifeSimulator.getNumberOfColumns(); j++) {
                drawBoard.setCellState(gameOfLifeSimulator.getGameState().getState(i, j), i, j);
            }
        }
        drawBoard.setVisible(true);
    }

    private boolean changeSimulationSize() {
        final Integer columns = tryToParse(JOptionPane.showInputDialog(this, "Podaj liczbę kolumn"));
        final Integer rows = tryToParse(JOptionPane.showInputDialog(this, "Podaj liczbę wierszy"));
        if (columns == null || rows == null || columns < 1 || rows < 1) {
            JOptionPane.showMessageDialog(this, "Wprowadzono niepoprawną wartość!");
            return false;
        }
        configureGameOfLife(rows, columns);
        drawBoard.setCellMapSize(rows, columns);
        drawBoard.configureGameCells();
        displayGameState();
        return true;
    }

    private Integer tryToParse(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (controlPanel == null) {
            return;
        }
        timer.setDelay(controlPanel.getAnimationSpeed());
        gameOfLifeSimulator.setRuleSet(controlPanel.getNewRuleSet());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseIsPressed = true;
        switchCellState(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseIsPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (mouseIsPressed) {
            switchCellState(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void switchCellState(MouseEvent e) {
        JPanel cell = (JPanel) e.getSource();
        final Point coordinates = drawBoard.getCoordinates(cell);
        gameOfLifeSimulator.getGameState().switchState(coordinates.x, coordinates.y);
        displayGameState();
    }
}
