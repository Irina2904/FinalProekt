package ua.goit.java8.project5;

import javafx.event.Event;
//package ua.goit.java8.project5;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by t.oleksiv on 27/09/2017.
 */
public class MediaResonance {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    public Pane root = new Pane();


    public void show(Event eventLast){
        Stage stage = new Stage();

        stage.setTitle("Media resonance");
        stage.setHeight(700);
        stage.setWidth(1000);
        stage.show();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.WINDOW_MODAL);

        stage.initOwner(
                ((Node)eventLast.getSource()).getScene().getWindow() );


        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });

        Text scenetitle = new Text("Введите ID Канала для анализа");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));




        TextField putText = new TextField();



    }

}