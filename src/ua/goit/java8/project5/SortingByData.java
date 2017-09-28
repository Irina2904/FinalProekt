package ua.goit.java8.project5;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.application.Application.launch;

public class SortingByData  {

    public Pane root = new Pane();



     public void start(Stage primaryStage) throws Exception {
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);

         Text scenetitle = new Text("Введите массив ID Каналов через пробел");
         scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
         scenetitle.setTranslateX(10);
         scenetitle.setTranslateY(10);



        final TextField channelId = new TextField(" ");
        channelId.setTranslateX(10);
        channelId.setTranslateY(30);

         Button back = new Button("Back");
         back.setTranslateX(10);
         back.setTranslateY(450);
         back.setOnMouseClicked(event -> {
             ((Node) (event.getSource())).getScene().getWindow().hide();
         });

        Button initButton = new Button("Найти данные");
        initButton.setTranslateX(150);
        initButton.setTranslateY(150);
        initButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    getActivity(root, channelId.getText());
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });

        root.getChildren().addAll(channelId, back, scenetitle, initButton);

        primaryStage.setHeight(700);
        primaryStage.setWidth(1000);
        primaryStage.show();
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

    public static void main(String[] args) {
        initApplication();
        launch(args);
    }

  private void getActivity(final Pane root, String channelId) throws UnirestException {
        Text actionTitle = new Text("Choose action:");
        actionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        TextField channelID = new TextField();
        String[] channelID2 = channelID.getText().split(" ");

      for (int i1 = 0; i1 < channelID2.length; i1++) {
            HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .queryString("part", "snippet,statistics")
                    .queryString("id", channelID2[i1])
                    .queryString("key", "AIzaSyAoJtBYFKQT3rF5Qy0P0hHEFdAMDnOXOhA")
                    .asObject(ActivityResponse.class);

            ActivityResponse activity = response.getBody();

            for(int i = 0; i < activity.items.size(); i++) {
                final Activity item = activity.items.get(i);

                String title =  item.snippet.localized.title;
                final Text button = new Text("Channel Name: " + (i)+title);
                button.setTranslateX(10);
                button.setTranslateY(50 * i + 10);

                String videoCount = item.statistics.videoCount;
                final Text videoCount2 = new Text("Количество видео на канале: " + i+videoCount);
                videoCount2.setTranslateX(100);
                videoCount2.setTranslateY(50 * i + 10);

                String publishedAt = item.snippet.publishedAt;
                final Text publishedAt2 = new Text("Дата создания канала: " + i+publishedAt);
                publishedAt2.setTranslateX(200);
                publishedAt2.setTranslateY(50 * i + 10);

                String subscriberCount = item.statistics.subscriberCount;
                final Text subscriberCount2 = new Text("Дата создания канала: " + i+subscriberCount);
                subscriberCount2.setTranslateX(250);
                subscriberCount2.setTranslateY(50 * i + 10);
                
                String viewCount = item.statistics.viewCount;
                final Text viewCount2 = new Text("Дата создания канала: " + i+viewCount);
                viewCount2.setTranslateX(300);
                viewCount2.setTranslateY(50 * i + 10);

                root.getChildren().addAll(button, videoCount2, publishedAt2);

                for (int i2 = 0; i2 < activity.items.size(); i2++) {
                    String[] subscriberCount3 = new String[activity.items.size()];
                    subscriberCount3[i] = subscriberCount;
                    sortingByViewCount(subscriberCount3);
                   // System.out.println(Arrays.asList(subscriberCount3));
                }
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