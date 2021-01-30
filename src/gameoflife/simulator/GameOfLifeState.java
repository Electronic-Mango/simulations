package gameoflife.simulator;

import java.util.Random;

public class GameOfLifeState {

    private static final int PADDING_CELLS = 2;
    private final int numberOfRows;
    private final int numberOfColumns;
    private boolean[][] gameState;

    public GameOfLifeState(final int numberOfRows, final int numberOfColumns) {
        gameState = new boolean[numberOfRows + PADDING_CELLS][numberOfColumns + PADDING_CELLS];
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public boolean getState(final int row, final int column) {
        checkIfCorrectCoordinates(row, column);
        return gameState[row + 1][column + 1];
    }

    public void setState(final boolean state, final int row, final int column) {
        checkIfCorrectCoordinates(row, column);
        gameState[row + 1][column + 1] = state;
    }

    public void setRandomState() {
        final Random randomGenerator = new Random();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                setState(randomGenerator.nextBoolean(), i, j);
            }
        }
    }

    public int calculateAliveNeighbours(final int numberOfRows, final int numberOfColumns) {
        checkIfCorrectCoordinates(numberOfRows, numberOfColumns);
        int numberOfAliveNeighbours = 0;
        numberOfAliveNeighbours += getState(numberOfRows - 1, numberOfColumns - 1) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows - 1, numberOfColumns) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows - 1, numberOfColumns + 1) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows, numberOfColumns - 1) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows, numberOfColumns + 1) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows + 1, numberOfColumns - 1) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows + 1, numberOfColumns) ? 1 : 0;
        numberOfAliveNeighbours += getState(numberOfRows + 1, numberOfColumns + 1) ? 1 : 0;
        return numberOfAliveNeighbours;
    }

    private void checkIfCorrectCoordinates(final int row, final int column) {
        if (row > numberOfRows || column > numberOfColumns) {
            final String exceptionMessage = String.format("Index %d %d out of bounds for state size %d %d", row, column, numberOfRows, numberOfColumns);
            throw new ArrayIndexOutOfBoundsException(exceptionMessage);
        }
    }

    public void switchState(final int x, final int y) {
        final boolean state = getState(x, y);
        setState(!state, x, y);
    }

}
