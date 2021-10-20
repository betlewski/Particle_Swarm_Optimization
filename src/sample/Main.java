package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/view.fxml"));
        primaryStage.setTitle("Particle Swarm Optimization");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // TODO:
    //  1. Change chart settings so that colors for different value levels are well chosen.
    //  2. Limit minimum and maximum range of X and Z values for particle [-4, 4].

}
