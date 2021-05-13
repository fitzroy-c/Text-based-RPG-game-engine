package gui;

import Info.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import src/src/Info.Player.java;

public class MainRoom extends DefaultRoom{

    Group root = new Group();
    Text playerName = new Text();

    public void initial() {
        playerName.setText(Player.class.getName());
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.show();
    }
}
