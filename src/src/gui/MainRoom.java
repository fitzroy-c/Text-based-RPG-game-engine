package gui;

import Player.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import src/src/Info.Player.java;

public class MainRoom extends DefaultRoom{

    Group root = new Group();
    Text playerName = new Text();
    TextField textField = new TextField("Please input your decision");

    public void initial() {
        playerName.setText(Player.class.getName());
        playerName.setLayoutX(25);
        playerName.setLayoutY(25);
        playerName.setFill(Color.WHITE);

        textField.setLayoutX(725);
        textField.setLayoutY(800);
        textField.setPrefSize(450,35);
        textField.requestFocus();
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        initial();
        root.getChildren().addAll(playerName,textField);
        Scene scene = new Scene(root,Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
