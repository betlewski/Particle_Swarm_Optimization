package sample.pso;

/**
 * Function types to chosen for optimization.
 */
public enum FunctionType {

    ACKLEY("Ackley", 0, 13),
    BOOTH("Booth", 0, 700),
    EGGHOLDER("Eggholder", -50, 0),
    GOLDSTEIN_PRICE("Goldstein-Price", 0, 100),
    MCCORMICK("McCormick", 0, 100),
    RASTRIGIN("Rastrigin", 0, 100),
    THREE_HUMP_CAMEL("Three-hump camel", 0, 500);

    private final String name;
    private final double minYRange;
    private final double maxYRange;

    FunctionType(String name, double minYRange, double maxYRange) {
        this.name = name;
        this.minYRange = minYRange;
        this.maxYRange = maxYRange;
    }

    public static FunctionType[] types() {
        return new FunctionType[]{
                ACKLEY, BOOTH, THREE_HUMP_CAMEL
        };
    }

    public String getName() {
        return name;
    }

    public double getMinYRange() {
        return minYRange;
    }

    public double getMaxYRange() {
        return maxYRange;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

