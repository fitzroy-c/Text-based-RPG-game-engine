package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


// contributed by Yixiang Yin
public class Gui extends Application{

//    private final Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        Stage popUpWindow = gui.popUpWindow.popUpWindow("Welcome to the world","Please enter your name below");
        popUpWindow.showAndWait();


        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
        stage.setTitle("game title");
        stage.setWidth(1500);
        stage.setHeight(1500);


        Text text = new Text();
        text.setText("Welcome to the world");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.RED);
        stage.setResizable(false);
        stage.setFullScreen(true);
        root.getChildren().add(text);

        stage.setScene(scene);
        stage.show();

        /*
         * created by Zihong Yuan.
         * this listening to mouse click event and
         * load into next scene.
         */
        scene.setOnMouseClicked((MouseEvent event) -> {
            stage.close();
            Stage s = new Stage();
            InitialStage ini = new InitialStage();
            ini.start(s);
        });
    }
}
