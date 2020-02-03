package task2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameOfLifeDrawBoard extends JPanel {

    private static final Color DEAD_COLOR = Color.WHITE;
    private static final Color ALIVE_COLOR = Color.BLUE;
    private final MouseListener mouseListener;
    private JPanel[][] gameCells;
    private int numberOfRows;
    private int numberOfColumns;

    public GameOfLifeDrawBoard(final int numberOfRows, final int numberOfColumns, final MouseListener mouseListener) {
        this.mouseListener = mouseListener;
        setCellMapSize(numberOfRows, numberOfColumns);
        configureGameCells();
    }

    public void setCellMapSize(final int numberOfRows, final int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        removeAll();
        setLayout(new GridLayout(numberOfRows, numberOfColumns));
    }

    public void configureGameCells() {
        gameCells = new JPanel[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                final JPanel cell = new JPanel();
                cell.addMouseListener(mouseListener);
                cell.setPreferredSize(new Dimension(getWidth() / numberOfRows, getHeight() / numberOfColumns));
                gameCells[row][column] = cell;
                add(cell);
            }
        }
    }

    public void setCellState(final boolean cellState, final int row, final int column) {
        if (cellState) {
            gameCells[row][column].setBackground(ALIVE_COLOR);
        } else {
            gameCells[row][column].setBackground(DEAD_COLOR);
        }
    }

    public Point getCoordinates(final JPanel cell) {
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                if (gameCells[row][column] == cell) return new Point(row, column);
            }
        }
        return null;
    }

}
