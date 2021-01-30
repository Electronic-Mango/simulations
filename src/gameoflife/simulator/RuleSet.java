package gameoflife.simulator;

public class RuleSet {

    private final int deathByLoneliness;
    private final int deathByCrowding;
    private final int parentsRequired;

    public RuleSet(final int deathByLoneliness, final int deathByCrowding, final int parentsRequired) {
        this.deathByLoneliness = deathByLoneliness;
        this.deathByCrowding = deathByCrowding;
        this.parentsRequired = parentsRequired;
    }

    public static RuleSet defaultRuleSet() {
        return new RuleSet(2, 3, 3);
    }

    public boolean isAlive(final int numberOfNeighbours, final boolean currentState) {
        if (numberOfNeighbours < deathByLoneliness) return false;
        if (numberOfNeighbours > deathByCrowding) return false;
        return currentState || (numberOfNeighbours == parentsRequired);
    }

}
