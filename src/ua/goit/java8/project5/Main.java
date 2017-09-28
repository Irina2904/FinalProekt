package ua.goit.java8.project5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Taras on 26.09.2017.
 */
public class Main extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 275;
    private GridPane grid = new GridPane();     //в якості layout використовуєм GridPane для зручності вирівнювання

    public static void main(String[] args) {
        launch();
    }
    private static void initApplication() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initApplication();
        //на випадок якщо буде потрібно використати парсер JSONа з Unirest asObject
        //MyObjectMapper myObjectMapper = new MyObjectMapper();

        //малюєм стартове вікно у GridPane
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, WIDTH, HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Main Screen");

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label appName = new Label("YouTube Monitor");
        grid.add(appName, 0, 1);

        Label versionNumber = new Label("version # 1.1");
        grid.add(versionNumber, 1, 1);

        Button buttonYouTubeAnalytics = new Button("YouTube Analytics");
        buttonYouTubeAnalytics.setOnMouseClicked(event -> {
            // ініціалізація вікна YouTubeAnalytics
            YouTubeAnalytics youTubeAnalytics = new YouTubeAnalytics();
            // запускаєм нове вікно в модальному виді
           // youTubeAnalytics.show(event);
        });

        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.CENTER);
        hbBtn1.getChildren().add(buttonYouTubeAnalytics);
        grid.add(hbBtn1, 0, 2);

        Button buttonSettings = new Button("Settings");
        buttonSettings.setOnMouseClicked(event -> {
            // ініціалізація вікна Settings
            Settings settings = new Settings();
            // запускаєм нове вікно в модальному виді
            settings.show(event);
        });

        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(buttonSettings);
        grid.add(hbBtn2, 1, 2);

        primaryStage.show();

    }
}