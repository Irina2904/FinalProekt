package ua.goit.java8.project5;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class YouTubeAnalytics {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;


    public void show(Event eventLast) throws Exception{
        final Pane root = new Pane();

        Stage stage = new Stage();
        GridPane grid = new GridPane();     //grid для зручності вирівнювання, а можна і Pane root
        stage.setTitle("YouTube Analytics");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        stage.setScene(new Scene(grid, WIDTH, HEIGHT));

        stage.initModality(Modality.WINDOW_MODAL);      //запускаєм вікно в модальному виді для того, щоб стартове вікно було неактивне

        //вказуєм нижче власника модального вікна по параметру event (в нашому випадку - це стартове вікно)
        stage.initOwner(
                ((Node)eventLast.getSource()).getScene().getWindow() );

        // заповнюєм елементами вікно
        // створюєм елементи, створюєм контейнери і запихаєм елементи в контейнери

        // кнопка Back
        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {

            ((Node)(event.getSource())).getScene().getWindow().hide();
        });


        Text scenetitle = new Text("YouTube Analytics");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 1, 1);


        Text actionTitle = new Text("Choose action:");
        actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        Button sortingByData = new Button("Sorting Channel by data");
        sortingByData.setOnMouseClicked(event -> {

            SortingByData sortingByData2 = new SortingByData();
           // sortingByData2.start(Stage);
        });

        Button mediaResonance = new Button("Media resonance");
        mediaResonance.setOnMouseClicked(event -> {

            MediaResonance mediaResonance2 = new MediaResonance();
            mediaResonance2.show(event);
        });



        // контейнер для  назви секції дій
        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPrefWidth(WIDTH/4);
        hbox2.setPrefHeight(HEIGHT/5);
        // додаєм сюди елемент
        hbox2.getChildren().add(actionTitle);
        grid.add(hbox2, 0, 1);


        // контейнер для  вибору дії
        HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setPrefWidth(WIDTH/4);
        hbox3.setPrefHeight(HEIGHT/5);
        hbox3.getChildren().add(sortingByData);
        // додаєм сюди елемент
        grid.add(hbox3, 0, 2);

        // контейнер для  кнопки Execute
        HBox hbox4 = new HBox(10);
        hbox4.setAlignment(Pos.CENTER_LEFT);
        hbox4.setPrefWidth(WIDTH/4);
        hbox4.setPrefHeight(HEIGHT/5);
        hbox4.getChildren().add(mediaResonance);
        grid.add(hbox4, 0, 3);

        // контейнер для кнопки Back
        HBox hbox5 = new HBox(10);
        hbox5.setAlignment(Pos.CENTER_LEFT);
        hbox5.setPrefWidth(WIDTH/4);
        hbox5.setPrefHeight(HEIGHT/5);
        hbox5.getChildren().add(back);
        grid.add(hbox5, 0, 4);

        // контейнер для виводу результатів
        HBox hbox6 = new HBox(10);
        hbox6.setAlignment(Pos.CENTER_LEFT);
        hbox6.setPrefWidth(WIDTH/2);
        hbox6.setPrefHeight(HEIGHT);
        // додаєм сюди елемент
        grid.add(hbox6, 1, 1,1,4);

        // приклад коду для закриття попереднього вікна, з якого було відкрито дане
        //((Node)(event.getSource())).getScene().getWindow().hide();

        stage.show();


    }

}