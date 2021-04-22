package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is contributed by Zihong Yuan.
 *
 * This is an initial game-play stage.
 * It's just a rough design at this stage, but can be implemented
 * more functionalities in the future.
 */
public class InitialStage extends Application {

    Group root = new Group();
    int WIDTH = 1280, HEIGHT = 720;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root,WIDTH,HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Game Name");
        primaryStage.show();
    }
}
