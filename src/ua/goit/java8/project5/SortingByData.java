package ua.goit.java8.project5;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.Event;
//package ua.goit.java8.project5;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Created by t.oleksiv on 27/09/2017.
 */
public class SortingByData {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;


    public void show(Event eventLast) throws UnirestException, NullPointerException, Exception{
        Stage stage = new Stage();
        GridPane grid = new GridPane();     //grid для зручності вирівнювання, а можна і Pane root
        stage.setTitle("Сортировать каналы по их данным");
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


        Button sortingByData = new Button("Sorting Channel by View Count");
        sortingByData.setOnMouseClicked(event -> {

            TaskSorting taskSorting = new TaskSorting();
            // запускаєм нове вікно в модальному виді
            taskSorting.show(event, Stage);
        });

        // String[] channelID = sc.nextLine().split(" ");

        HBox hbox2 = new HBox(10);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPrefWidth(WIDTH/4);
        hbox2.setPrefHeight(HEIGHT/5);
        // додаєм сюди елемент
        hbox2.getChildren().add(putText);
        grid.add(hbox2, 0, 1);


        // контейнер для  вибору дії
        /*HBox hbox3 = new HBox(10);
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setPrefWidth(WIDTH/4);
        hbox3.setPrefHeight(HEIGHT/5);
        hbox2.getChildren().add(sortingByData);
        // додаєм сюди елемент
        grid.add(hbox3, 0, 2);*/

        // контейнер для  кнопки Execute
        HBox hbox4 = new HBox(10);
        hbox4.setAlignment(Pos.CENTER_LEFT);
        hbox4.setPrefWidth(WIDTH/4);
        hbox4.setPrefHeight(HEIGHT/5);
        // додаєм сюди елемент
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

    public void start(Stage primaryStage) throws Exception {
        final Pane root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        final TextField channelId = new TextField("UCjamiTU85WjDKkfgsGSqi7w");
        channelId.setTranslateX(150);
        channelId.setTranslateY(60);

        final TextField maxResults = new TextField("5");
        maxResults.setTranslateX(150);
        maxResults.setTranslateY(110);

        Button initButton = new Button("Init");
        initButton.setTranslateX(150);
        initButton.setTranslateY(10);
        initButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    getActivity(root, channelId.getText(), maxResults.getText());
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });


        primaryStage.setHeight(700);
        primaryStage.setWidth(1000);
        primaryStage.show();

        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {
            // запускаєм нове вікно в модальному виді
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
        // назва вікна
        Text scenetitle = new Text("Введите массив ID Каналов через пробел");

        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        // назва вікна
        Text actionTitle = new Text("Choose action:");
        actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        // контейнер для  назви секції дій
        TextField channelID = new TextField();
        String[] channelID2 = channelID.getText().split(" ");

        root.getChildren().addAll(initButton, channelId, maxResults, back);

        for (int i1 = 0; i1 < channelID2.length; i1++) {

            HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .queryString("part", "snippet,statistics")
                    .queryString("id", channelID2[i1])
                    .queryString("key", "AIzaSyAoJtBYFKQT3rF5Qy0P0hHEFdAMDnOXOhA")
                    .asObject(ActivityResponse.class);

            ActivityResponse activity = response.getBody();

            for(int i = 0; i < activity.items.size(); i++) {
                final Activity item = activity.items.get(i);

                final TextField button = new TextField("Video " + (i+1));
                button.setTranslateX(10);
                button.setTranslateY(50 * i + 10);
                root.getChildren().addAll(button);



                String subscriberCount2 = item.statistics.subscriberCount;
                String videoCount = item.statistics.videoCount;
                String publishedAt = item.snippet.publishedAt;
                String viewCount = item.statistics.viewCount;
                String title =  item.snippet.localized.title;

                for (int i2 = 0; i2 < activity.items.size(); i2++) {
                    String[] subscriberCount3 = new String[activity.items.size()];
                    subscriberCount3[i] = subscriberCount2;
                    sortingByViewCount(subscriberCount3);
                   // System.out.println(Arrays.asList(subscriberCount3));
                }

                /*System.out.println("Кол-во подписчиков: " + subscriberCount2);
                System.out.println("Дата создания канала:  " + publishedAt);
                System.out.println("Кол-во просмотров всех видео:  " + viewCount);
                System.out.println("Кол-во видео:  " + videoCount);
                System.out.println("Название канала: " + title);*/


            }
        }
    }

    public static void sortingByViewCount(String [] array1){
        int array[] = new int[array1.length];
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                int current = array[i];
                array[i] = array[i + 1];
                array[i + 1] = current;
            }
        }
    }
    }

}