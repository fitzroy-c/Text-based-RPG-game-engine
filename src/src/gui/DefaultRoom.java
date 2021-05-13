package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class DefaultRoom extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setHeight(1080);
        primaryStage.setWidth(1920);
        primaryStage.setTitle("MoonNight");
    }

}
