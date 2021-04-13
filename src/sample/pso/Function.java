package sample.pso;

public class Function {
    /**
     * Perform Ackley's function.
     * Domain is [5, 5]
     * Minimum is 0 at x = 0 & z = 0.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    public static double ackleysFunction(double x, double z) {
        double p1 = -20 * Math.exp(-0.2 * Math.sqrt(0.5 * ((x * x) + (z * z))));
        double p2 = Math.exp(0.5 * (Math.cos(2 * Math.PI * x) + Math.cos(2 * Math.PI * z)));
        return p1 - p2 + Math.E + 20;
    }

    /**
     * Perform Booth's function.
     * Domain is [-5, 5]
     * Minimum is 0 at x = 1 & z = 3.
     *
     * @param x the x component
     * @param z the z component
     * @return the y component
     */
    public static double boothsFunction(double x, double z) {
        double p1 = Math.pow(x + 2 * z - 7, 2);
        double p2 = Math.pow(2 * x + z - 5, 2);
        return p1 + p2;
    }

    /**
     * Perform the Three-Hump Camel function.
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
