package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


// contributed by Yixiang Yin
public class Gui extends Application{

//    private final Group root = new Group();

    public static void main(String[] args) {
        launch(args);
        // the start frame

//        ImageIcon image = new ImageIcon("logo.png");
//        JLabel label = new JLabel();
//        label.setText("Welcome to the world ...");
//        label.setIcon(image);
//        label.setHorizontalTextPosition(JLabel.CENTER);
//        label.setVerticalTextPosition(JLabel.TOP);
//        label.setForeground(new Color(0x00FF00));
//        label.setFont(new Font("MV Boli",Font.PLAIN,20));
//        label.setBackground(Color.black);
//        label.setOpaque(true);
//        label.setVerticalAlignment(JLabel.CENTER);
//        label.setBounds(0,0,250,250);



//        Myframe startFrame = new Myframe();
//        startFrame.setLayout(null);
//        startFrame.add(label);

        /*
         * created by Zihong Yuan.
         * this listening to mouse click event and
         * load into next scene.
         */
//        startFrame.getContentPane().addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Click");
//                startFrame.dispose();
//                Application.launch(InitialStage.class);
//            }
//        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("title");
        popUpWindow.setMinWidth(250);
        popUpWindow.setMinHeight(250);
        Label label = new Label();

        label.setText("message");



        TextField textField = new TextField();
        textField.setLayoutX(100);
        textField.setLayoutY(100);
        textField.setPrefSize(200,35);
//        textField.requestFocus();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,textField);
        layout.setAlignment(Pos.CENTER);
        Scene popUpScene = new Scene(layout);
        popUpWindow.setScene(popUpScene);
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


    }
}
