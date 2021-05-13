package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is contributed by Zihong Yuan.
 *
 * This is an initial game-play scene.
 * It's just a rough design at this stage, but can be implemented
 * more functionalities in the future.
 */
public class InitialStage extends Application {

    int WIDTH = 1280, HEIGHT = 720;
    int HP = 100, MP = 100;

    Group root = new Group();
    StackPane hp = new StackPane();
    StackPane mp = new StackPane();


    TextField textField = new TextField("Please input your action");
    Rectangle hpBar = new Rectangle(300,30,Color.RED);
    Text hpText = new Text("HP");
    Rectangle mpBar = new Rectangle(250,30,Color.BLUE);
    Text mpText = new Text("MP");
    ImageView monster = new ImageView(new Image("gui/Monster-Cthulhu.png"));

    public void hpANDmp() {
        hp.getChildren().addAll(hpBar,hpText);
        mp.getChildren().addAll(mpBar,mpText);
    }

    public void monsterPic(ImageView m) {
        m.setFitHeight(300);
        m.setFitWidth(450);
        m.setLayoutX(400);
        m.setLayoutY(100);
    }

    @Override
    public void start(Stage primaryStage) {
        textField.setLayoutX(400);
        textField.setLayoutY(600);
        textField.setPrefSize(450,35);
        textField.requestFocus();

        hpANDmp();
        hp.setLayoutX(25);
        hp.setLayoutY(25);
        mp.setLayoutX(25);
        mp.setLayoutY(60);

        monsterPic(monster);

        root.getChildren().addAll(textField,hp,mp,monster);
        Scene scene = new Scene(root,WIDTH,HEIGHT, Color.BLACK);
        // just to set it to not be resizable and open the full screen mode. (edited by Yixiang Yin)
//        primaryStage.setResizable(true);
//        primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Game Name");
        primaryStage.show();

        //Press Escape on keyboard to exit the program. For the test purpose.
//        scene.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode()== KeyCode.ESCAPE) {System.exit(0);}
//        });
    }
}
