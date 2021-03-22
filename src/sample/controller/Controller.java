package sample.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import sample.pso.FunctionType;
import sample.pso.NeighbourhoodType;
import sample.pso.Swarm;

public class Controller {

    @FXML
    private ToggleButton actionButton;
    @FXML
    private ComboBox<FunctionType> functionComboBox;
    @FXML
    private ComboBox<NeighbourhoodType> neighbourhoodComboBox;
    @FXML
    private Label particlesLabel;
    @FXML
    private Slider particlesSlider;
    @FXML
    private Label epochsLabel;
    @FXML
    private Slider epochsSlider;
    @FXML
    private Label inertionLabel;
    @FXML
    private Slider inertionSlider;
    @FXML
    private Label cognitiveLabel;
    @FXML
    private Slider cognitiveSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label socialLabel;
    @FXML
    private Slider socialSlider;
    @FXML
    private TextArea chart;
    @FXML
    private ProgressBar chartProgress;
    @FXML
    private StackPane surfaceChartPane;
    @FXML
    private StackPane scatterPlotChartPane;

    private Swarm pso;
    private static FunctionType FUNCTION_TYPE = FunctionType.ACKLEYS;
    private static NeighbourhoodType NEIGHBOURHOOD_TYPE = NeighbourhoodType.GLOBAL;
    private static int PARTICLES_NUMBER = 10;
    private static int EPOCHS_NUMBER = 10;
    private static double INERTION_VALUE = 1.0;
    private static double COGNITIVE_VALUE = 1.0;
    private static double SOCIAL_VALUE = 1.0;
    private static int SLEEP_SPEED = 5;
    private static final double PRECISION = 100.0;

    public void initialize() {
        setActionButtonListener();
        initFunctionComboBox();
        initNeighbourhoodComboBox();
        setSlidersListeners();
        chart.setEditable(false);
    }

    private void setActionButtonListener() {
        actionButton.selectedProperty().addListener((observable, oldValue, actualValue) -> {
            if (actualValue) {
                actionButton.setText("STOP");
                disableControls(true);
                pso = new Swarm(FUNCTION_TYPE, NEIGHBOURHOOD_TYPE, PARTICLES_NUMBER,
                        EPOCHS_NUMBER, INERTION_VALUE, COGNITIVE_VALUE, SOCIAL_VALUE, SLEEP_SPEED);
                new Thread(() -> pso.run(chart, scatterPlotChartPane)).start();
                Node chartNode = new SurfaceChart().createChartNode(FUNCTION_TYPE);
                surfaceChartPane.getChildren().add(chartNode);
            } else {
                actionButton.setText("START");
                disableControls(false);
                chart.clear();
                surfaceChartPane.getChildren().clear();
            }
        });
    }

    private void initFunctionComboBox() {
        FunctionType[] functionTypes = FunctionType.values();
        functionComboBox.setItems(FXCollections.observableArrayList(functionTypes));
        functionComboBox.setValue(FunctionType.ACKLEYS);
        functionComboBox.valueProperty().addListener(
                (observable, oldValue, actualValue) -> FUNCTION_TYPE = actualValue);
    }

    private void initNeighbourhoodComboBox() {
        NeighbourhoodType[] neighbourTypes = NeighbourhoodType.values();
        neighbourhoodComboBox.setItems(FXCollections.observableArrayList(neighbourTypes));
        neighbourhoodComboBox.setValue(NeighbourhoodType.GLOBAL);
        neighbourhoodComboBox.valueProperty().addListener(
                (observable, oldValue, actualValue) -> NEIGHBOURHOOD_TYPE = actualValue);
    }

    private void disableControls(boolean value) {
        functionComboBox.setDisable(value);
        neighbourhoodComboBox.setDisable(value);
        particlesSlider.setDisable(value);
        epochsSlider.setDisable(value);
        inertionSlider.setDisable(value);
        cognitiveSlider.setDisable(value);
        socialSlider.setDisable(value);
    }

    private void setSlidersListeners() {
        particlesSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            int value = actualValue.intValue();
            PARTICLES_NUMBER = value;
            particlesLabel.setText(String.valueOf(value));
        });
        epochsSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            int value = actualValue.intValue();
            EPOCHS_NUMBER = value;
            epochsLabel.setText(String.valueOf(value));
        });
        inertionSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            double value = Math.round(actualValue.doubleValue() * PRECISION) / PRECISION;
            INERTION_VALUE = value;
            inertionLabel.setText(String.valueOf(value));
        });
        cognitiveSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            double value = Math.round(actualValue.doubleValue() * PRECISION) / PRECISION;
            COGNITIVE_VALUE = value;
            cognitiveLabel.setText(String.valueOf(value));
        });
        socialSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            double value = Math.round(actualValue.doubleValue() * PRECISION) / PRECISION;
            SOCIAL_VALUE = value;
            socialLabel.setText(String.valueOf(value));
        });
        speedSlider.valueProperty().addListener((observable, oldValue, actualValue) -> {
            int value = actualValue.intValue();
            SLEEP_SPEED = value;
            pso.setSleepSpeed(value);
        });
    }

}
