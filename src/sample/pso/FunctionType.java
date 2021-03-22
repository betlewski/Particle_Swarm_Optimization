package sample.pso;

/**
 * Function types to chosen for optimization.
 */
public enum FunctionType {

    ACKLEYS("Ackley's"),
    BOOTHS("Booth's"),
    THREE_HUMP_CAMEL("Three-hump camel");

    public final String name;

    FunctionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

