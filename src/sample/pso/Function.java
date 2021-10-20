package sample.pso;

public class Function {

    public static FunctionType functionType;

    public static double getValueInPoint(double x, double z) {
        switch (functionType) {
            case ACKLEY:
                return ackleyFunction(x, z);
            case BOOTH:
                return boothFunction(x, z);
            case EGGHOLDER:
                return eggholderFunction(x, z);
            case GOLDSTEIN_PRICE:
                return goldsteinPriceFunction(x, z);
            case MCCORMICK:
                return mcCormickFunction(x, z);
            case RASTRIGIN:
                return rastriginFunction(x, z);
            case THREE_HUMP_CAMEL:
                return threeHumpCamelFunction(x, z);
            default:
                throw new IllegalArgumentException("Optimization function has not been chosen.");
        }
    }

    /**
     * Perform Ackley function.
     * Domain is [-4, 4]
     * Minimum is 0 at x = 0 & z = 0.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    public static double ackleyFunction(double x, double z) {
        double p1 = -20 * Math.exp(-0.2 * Math.sqrt(0.5 * ((x * x) + (z * z))));
        double p2 = Math.exp(0.5 * (Math.cos(2 * Math.PI * x) + Math.cos(2 * Math.PI * z)));
        return p1 - p2 + Math.E + 20;
    }

    /**
     * Perform Booth function.
     * Domain is [-4, 4]
     * Minimum is 0 at x = 1 & z = 3.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    public static double boothFunction(double x, double z) {
        double p1 = Math.pow(x + 2 * z - 7, 2);
        double p2 = Math.pow(2 * x + z - 5, 2);
        return p1 + p2;
    }

    /**
     * Perform Eggholder function.
     * Domain is [-4, 4]
     * Minimum is -45 at x = 4 & y = 4.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    private static double eggholderFunction(double x, double z) {
        double p1 = -(z + 47) * Math.sin(Math.sqrt(Math.abs(z + x / 2 + 47)));
        double p2 = -x * Math.sin(Math.sqrt(Math.abs(x - (z + 47))));
        return p1 + p2;
    }

    /**
     * Perform Goldstein-Price function.
     * Domain is [-4, 4]
     * Minimum is 3 at x = 0 & z = -1.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    private static double goldsteinPriceFunction(double x, double z) {
        double p1a = Math.pow((x + z + 1), 2);
        double p1b = 19 - 14 * x + 3 * Math.pow(x, 2) - 14 * z + 6 * x * z + 3 * Math.pow(z, 2);
        double p1 = 1 + p1a * p1b;
        double p2a = Math.pow((2 * x - 3 * z), 2);
        double p2b = 18 - 32 * x + 12 * Math.pow(x, 2) + 48 * z - 36 * x * z + 27 * Math.pow(z, 2);
        double p2 = 30 + p2a * p2b;
        return p1 * p2;
    }

    /**
     * Perform McCormick function.
     * Domain is [-4, 4]
     * Minimum is -4.44 at x = -3.45 & z = -4.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    private static double mcCormickFunction(double x, double z) {
        double p1 = Math.sin(x + z) + Math.pow(x - z, 2);
        double p2 = -1.5 * x + 2.5 * z + 1;
        return p1 + p2;
    }

    /**
     * Perform Rastrigin function.
     * Domain is [-4, 4]
     * Minimum is 0 at x = 0 & z = 0.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    private static double rastriginFunction(double x, double z) {
        double p1 = Math.pow(x, 2) - 10 * Math.cos(2 * Math.PI * x);
        double p2 = Math.pow(z, 2) - 10 * Math.cos(2 * Math.PI * z);
        return 20 + p1 + p2;
    }

    /**
     * Perform the Three-Hump Camel function.
     * Domain is [-4, 4]
     * Minimum is 0 at x = 0 & z = 0.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    public static double threeHumpCamelFunction(double x, double z) {
        double p1 = 2 * x * x;
        double p2 = 1.05 * Math.pow(x, 4);
        double p3 = Math.pow(x, 6) / 6;
        return p1 - p2 + p3 + x * z + z * z;
    }

}
