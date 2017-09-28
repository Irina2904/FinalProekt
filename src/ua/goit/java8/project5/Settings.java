package ua.goit.java8.project5;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by t.oleksiv on 27/09/2017.
 */
public class Settings {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;

    public void show(Event eventLast){
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

        stage.show();
    }
}