package sample.pso;

/**
 * Neighbourhood types to chosen for optimization.
 */
public enum NeighbourhoodType {

    NO("No"),
    GLOBAL("Global"),
    STAR_TOPOLOGY("Star topology");

    public final String name;

    NeighbourhoodType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

