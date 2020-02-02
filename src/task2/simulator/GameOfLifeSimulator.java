package task2.simulator;

public class GameOfLifeSimulator {

    private GameOfLifeState gameState = new GameOfLifeState();
    private int numberOfRows = 0;
    private int numberOfColumns = 0;
    private RuleSet ruleSet = RuleSet.defaultRuleSet();

    public void createGameOfLifeState(final int numberOfRows, final int numberOfColumns) {
        gameState = new GameOfLifeState(numberOfRows, numberOfColumns);
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public void setRuleSet(final RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public void calculateNewGameState() {
        GameOfLifeState newGameState = new GameOfLifeState(numberOfRows, numberOfColumns);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                final int numberOfNeighbours = gameState.calculateAliveNeighbours(i, j);
                final boolean currentState = gameState.getState(i, j);
                final boolean newState = ruleSet.isAlive(numberOfNeighbours, currentState);
                newGameState.setState(newState, i, j);
            }
        }
        gameState = newGameState;
    }

    public void setRandomGameState() {
        gameState.setRandomState();
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public GameOfLifeState getGameState() {
        return gameState;
    }

    public void clearGameState() {
        gameState = new GameOfLifeState(numberOfRows, numberOfColumns);
    }
}
