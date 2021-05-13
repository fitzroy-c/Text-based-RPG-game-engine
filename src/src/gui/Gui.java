package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
Main gui class
*/
// contributed by Yixiang Yin
public class Gui extends Application{


    Stage window1;
    Stage window2;

    Scene Scene1;
    Scene Scene2;

    @Override
    public void start(Stage stage) throws Exception {
        window1 =stage;
        Label introText = new Label("Background: You are the Little Red Riding Hood. Recently, your grandma seems to be lost. You've been all the places in the town " +
                "except the black forest in front of you and finally decide to walk into the forest to find your grandma");
        introText.setTextFill(Color.WHITE);
        introText.setFont(Font.font("Verdana",50));
        introText.setWrapText(true);

        Button startB = new Button("Start the adventure");
        startB.setOnAction(e-> {
            // switch scenes
            window2.showAndWait();

        });
        VBox layout1 = new VBox(200);
        layout1.setStyle("-fx-background-color: black"); // set the background color
        layout1.getChildren().addAll(introText,startB);
        Scene1 =  new Scene(layout1, 1000,1000);
//        Image icon = new Image("logo.png");
//        stage.getIcons().add(icon);

        Text text2 = new Text("Please enter your name and choose your career below");
        window1.setScene(Scene1);

        window1.setTitle("game title");
        window1.setWidth(1500);
        window1.setHeight(1500);
        window1.show();

        // set up scene2
        Label enterNameText = new Label("Please enter your name below");
        enterNameText.setTextFill(Color.WHITE);
        enterNameText.setFont(Font.font("Verdana",50));
        enterNameText.setWrapText(true);

        // window 2
        Button okButton = new Button("OK");
        TextField nameField = new TextField("Please enter your name below");

        okButton.setOnAction(e-> {
            String name = nameField.getText();
            System.out.println(name);
            window2.close();
            window1.close();
            InitialStage is = new InitialStage();
            Stage s = new Stage();
            is.start(s);

        });

        VBox layout2 = new VBox();
        layout2.setStyle("-fx-background-color: black"); // set the background color
        layout2.getChildren().addAll(nameField,okButton);
        Scene2 = new Scene(layout2,500,500);
        window2 = new Stage();
        window2.setScene(Scene2);
        window2.initModality(Modality.APPLICATION_MODAL);



        /*
         * created by Zihong Yuan.
         * this listening to mouse click event and
         * load into next scene.
         */
//        scene.setOnMouseClicked((MouseEvent event) -> {
//            stage.close();
//            Stage s = new Stage();
//            InitialStage ini = new InitialStage();
//            ini.start(s);
//        });

        /**
         * Build a game
         * - A variable to say whether this is a fresh game or already played game
         * - Create player instance ( or load player instance from json (blank))
         * -
         *
         *
         * - Main game:
         * -  enter rooms (various rooms (blessing, destroy, upgrade))
         * -  enter fight scene
         * -
         */


    }
}
