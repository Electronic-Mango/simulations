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
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                final JPanel cell = new JPanel();
                cell.addMouseListener(mouseListener);
                cell.setPreferredSize(new Dimension(getWidth() / numberOfRows, getHeight() / numberOfColumns));
                gameCells[i][j] = cell;
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
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (gameCells[i][j] == cell) return new Point(i, j);
            }
        }
        return null;
    }
}
