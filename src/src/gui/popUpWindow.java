package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class popUpWindow {

    public static Stage popUpWindow(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(250);
        Label label = new Label();

        label.setText(message);


        TextField textField = new TextField();
        textField.setLayoutX(100);
        textField.setLayoutY(100);
        textField.setPrefSize(200,35);
        //        textField.requestFocus();


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,textField);
        layout.setAlignment(Pos.CENTER);
//
        Scene scene = new Scene(layout);
        window.setScene(scene);
//        window.showAndWait();
        return window;
    }
}
