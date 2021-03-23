package sample.pso;

import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import sample.controller.ScatterPlotChart;

import java.util.Random;

/**
 * Represents a swarm of particles from the Particle Swarm Optimization algorithm.
 */
public class Swarm {

    private Particle[] particles;
    private int numOfParticles, epochs;
    private double inertia, cognitiveComponent, socialComponent;
    private Vector bestPosition;
    private double bestEval;
    private FunctionType function; // The function to search.
    private NeighbourhoodType neighbourhoodType; // Neighbourhood type to use.
    public static final double DEFAULT_INERTIA = 0.5;
    public static final double DEFAULT_COGNITIVE = 1.5; // Cognitive component.
    public static final double DEFAULT_SOCIAL = 1.0; // Social component.

    /**
     * Values associated with sleeping time
     * between displaying two following dataset on the chart.
     */
    private int sleepSpeed; // Speed of sleeping time.
    public static final int DEFAULT_SLEEP_SPEED = 5;
    public static final int SLEEP_TIME = 200; // Sleeping time in millis.
    private boolean termination = false;

    /**
     * When Particles are created they are given a random position.
     * The random position is selected from a specified range.
     * If the begin range is 0 and the end range is 10 then the
     * value will be between 0 (inclusive) and 10 (exclusive).
     */
    private int beginRange, endRange;
    private static final int DEFAULT_BEGIN_RANGE = -5;
    private static final int DEFAULT_END_RANGE = 5;

    /**
     * Construct the Swarm with default values.
     *
     * @param particles the number of particles to create
     * @param epochs    the number of generations
     */
    public Swarm(FunctionType function, NeighbourhoodType neighbourhoodType, int particles, int epochs) {
        this(function, neighbourhoodType, particles, epochs, DEFAULT_INERTIA, DEFAULT_COGNITIVE, DEFAULT_SOCIAL, DEFAULT_SLEEP_SPEED);
    }

    /**
     * Construct the Swarm with custom values.
     *
     * @param particles the number of particles to create
     * @param epochs    the number of generations
     * @param inertia   the particles resistance to change
     * @param cognitive the cognitive component or introversion of the particle
     * @param social    the social component or extroversion of the particle
     */
    public Swarm(FunctionType function, NeighbourhoodType neighbourhoodType, int particles, int epochs, double inertia, double cognitive, double social, int sleepSpeed) {
        this.numOfParticles = particles;
        this.epochs = epochs;
        this.inertia = inertia;
        this.cognitiveComponent = cognitive;
        this.socialComponent = social;
        this.function = function;
        this.neighbourhoodType = neighbourhoodType;
        this.sleepSpeed = sleepSpeed;
        double infinity = Double.POSITIVE_INFINITY;
        bestPosition = new Vector(infinity, infinity, infinity);
        bestEval = Double.POSITIVE_INFINITY;
        beginRange = DEFAULT_BEGIN_RANGE;
        endRange = DEFAULT_END_RANGE;
    }

    /**
     * Set speed of sleeping time.
     *
     * @param speed value to set
     */
    public void setSleepSpeed(int speed) {
        this.sleepSpeed = speed;
    }

    /**
     * Terminate executing the algorithm.
     */
    public void terminate() {
        this.termination = true;
    }

    /**
     * Execute the algorithm.
     */
    public void run(StackPane chart, ProgressBar chartProgress) {
        particles = initialize();

        double oldEval = bestEval;
        System.out.println("--------------------------EXECUTING-------------------------");

        for (int i = 0; i < epochs; i++) {
            if (termination) {
                System.out.println("----------------------------STOP----------------------------");
                break;
            }
            XYZSeries<String> series = new XYZSeries<>("");
            System.out.println("Epoch " + (i + 1) + ":");

            if (bestEval < oldEval) {
                oldEval = bestEval;
            }

            for (Particle p : particles) {
                p.updatePersonalBest();
                updateGlobalBest(p);
            }

            for (Particle p : particles) {
                updateVelocity(p);
                p.updatePosition();
                System.out.println(p.getPosition().toString() + "");
                series.add(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
            }

            Platform.runLater(() -> {
                chart.getChildren().clear();

                XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
                dataset.add(series);

                Node chartNode = new ScatterPlotChart().createChartNode(dataset);
                chart.getChildren().add(chartNode);

                chartProgress.setProgress(chartProgress.getProgress() + (1.0 / epochs));
            });
            try {
                Thread.sleep(SLEEP_TIME * sleepSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("---------------------------RESULT---------------------------");
        System.out.println("x = " + bestPosition.getX() + "");
        System.out.println("y = " + bestPosition.getY() + "");
        System.out.println("Final Best Evaluation: " + bestEval + "");
        System.out.println("---------------------------COMPLETED-------------------------");
    }

    /**
     * Create a set of particles, each with random starting positions.
     *
     * @return an array of particles
     */
    private Particle[] initialize() {
        Particle[] particles = new Particle[numOfParticles];
        for (int i = 0; i < numOfParticles; i++) {
            Particle particle = new Particle(function, beginRange, endRange);
            particles[i] = particle;
            updateGlobalBest(particle);
        }
        return particles;
    }

    /**
     * Update the global best solution if a the specified particle has
     * a better solution
     *
     * @param particle the particle to analyze
     */
    private void updateGlobalBest(Particle particle) {
        if (particle.getBestEval() < bestEval) {
            bestPosition = particle.getBestPosition();
            bestEval = particle.getBestEval();
        }
    }

    /**
     * Update the velocity of a particle using the velocity update formula
     *
     * @param particle the particle to update
     */
    private void updateVelocity(Particle particle) {
        Vector oldVelocity = particle.getVelocity();
        Vector pBest = particle.getBestPosition();
        Vector gBest = getGlobalBest(particle);
        Vector pos = particle.getPosition();

        Random random = new Random();
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        // The first product of the formula.
        Vector newVelocity = oldVelocity.clone();
        newVelocity.mul(inertia);

        // The second product of the formula.
        pBest.sub(pos);
        pBest.mul(cognitiveComponent);
        pBest.mul(r1);
        newVelocity.add(pBest);

        // The third product of the formula.
        gBest.sub(pos);
        gBest.mul(socialComponent);
        gBest.mul(r2);
        newVelocity.add(gBest);

        particle.setVelocity(newVelocity);
    }

    /**
     * Get the global best value depending on neighbourhood type
     *
     * @param particle the particle to update
     */
    private Vector getGlobalBest(Particle particle) {
        switch (neighbourhoodType) {
            case NO:
                return particle.getBestPosition(); // gBest = pBest
            case GLOBAL:
                return bestPosition.clone(); // The best global position.
            case STAR_TOPOLOGY:
                return particles[0].getBestPosition(); // The first particle in table is the star of topology.
            default:
                throw new IllegalArgumentException("The neighbourhood type to use has not been set.");
        }
    }

}