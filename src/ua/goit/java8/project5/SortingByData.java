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

public class SortingByData implements Comparable  {

    public Pane root = new Pane();

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


     public void show(Event eventLast) throws Exception {
         Stage stage = new Stage();
         stage.setScene(new Scene(root));

       Button back = new Button("Back");
         back.setTranslateX(150);
         back.setTranslateY(450);
         back.setOnMouseClicked(event -> {
             ((Node) (event.getSource())).getScene().getWindow().hide();
         });
         Text scenetitle = new Text("Введите массив ID каналов через пробел");
         scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
         scenetitle.setTranslateX(150);
         scenetitle.setTranslateY(50);

         TextField channelId = new TextField("");
         channelId.setTranslateX(150);
         channelId.setTranslateY(60);
         String[] channelID2 = channelId.getText().split(" ");

        Button initButton = new Button("Найти данные");
        initButton.setTranslateX(150);
        initButton.setTranslateY(150);
        initButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    getActivity(root, channelID2);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(back, initButton, scenetitle, channelId);
         stage.setHeight(700);
         stage.setWidth(1000);
         stage.show();
    }

    public static void main(String[] args) {

    }

  private void getActivity(final Pane root, String[] channelID2) throws UnirestException {
        for (int i1 = 0; i1 < channelID2.length; i1++) {
            HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .queryString("part", "snippet,statistics")
                    .queryString("id", channelID2)
                    .queryString("key", "AIzaSyAoJtBYFKQT3rF5Qy0P0hHEFdAMDnOXOhA")
                    .asObject(ActivityResponse.class);

            ActivityResponse activity = response.getBody();

            for(int i = 0; i < activity.items.size(); i++) {
                final Activity item = activity.items.get(i);

                //сравниваем по имени
                if (activity.items.get(i).snippet.localized.title.compareTo(activity.items.get(i+1).snippet.localized.title) == 1) {
                    String current = activity.items.get(i).snippet.localized.title;
                    activity.items.get(i).snippet.localized.title = activity.items.get(i+1).snippet.localized.title;
                    activity.items.get(i+1).snippet.localized.title = current;
                };


                final Text button = new Text("Название канала: " + item.snippet.localized.title);
                button.setTranslateX(400 * (i+1));
                button.setTranslateY(100  + 10);

                final Text videoCount2 = new Text("Количество видео на канале: " + i + item.statistics.videoCount);
                videoCount2.setTranslateX(400 * (i+1));
                videoCount2.setTranslateY(120  + 10);

                final Text publishedAt2 = new Text("Дата создания канала: " + i + item.snippet.publishedAt);
                publishedAt2.setTranslateX(400 * (i+1));
                publishedAt2.setTranslateY(140  + 10);

                final Text subscriberCount2 = new Text("Количество подписчиков: " + i + item.statistics.subscriberCount);
                subscriberCount2.setTranslateX(400 * (i+1));
                subscriberCount2.setTranslateY(160  + 10);

                final Text viewCount2 = new Text("Дата создания канала: " + i + item.statistics.viewCount);
                viewCount2.setTranslateX(400 * (i+1));
                viewCount2.setTranslateY(180  + 10);

                root.getChildren().addAll(button, videoCount2, publishedAt2, subscriberCount2, viewCount2);

            }
      }
  }

    public static void sortingByTitle(String [] array1){
        int array[] = new int[array1.length];
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                int current = array[i];
                array[i] = array[i + 1];
                array[i + 1] = current;
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}