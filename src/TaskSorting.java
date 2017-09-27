/*Сортировать каналы по их данным
Принимает:
массив channelIds
сортировать по:
имени канала
дате создания
кол-ву подписчиков
кол-ву видео на канале
кол-ву просмотров всех видео

Отображает: весь список каналов отсортировав их предварительно.
Информация о каждом канале:
Имя канала
Дата создания канала
Кол-во подписчиков
Кол-во видео на канале
Кол-во просмотров всех видео
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.Scanner;

public class TaskSorting {
    public TaskSorting() throws UnirestException {
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
    

    public static void main(String[] args) throws UnirestException, NullPointerException {

        System.out.println("Введите массив ID видео через пробел");
        Scanner sc = new Scanner(System.in);

        String[] channelID = sc.nextLine().split(" ");
        initApplication();

        for (int i6 = 0; i6 < channelID.length; i6++) {

            HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .queryString("part", "snippet,statistics")
                    .queryString("id", channelID[i6])
                    .queryString("key", "AIzaSyAoJtBYFKQT3rF5Qy0P0hHEFdAMDnOXOhA")
                    .asObject(ActivityResponse.class);

            ActivityResponse activity = response.getBody();

            for (int i = 0; i < activity.items.size(); i++) {

                final Activity item = activity.items.get(i);

                String subscriberCount2 = item.statistics.subscriberCount;
                String videoCount = item.statistics.videoCount;
                String publishedAt = item.snippet.publishedAt;
                String viewCount = item.statistics.viewCount;
                System.out.println("Кол-во подписчиков: " + subscriberCount2);
                System.out.println("Дата создания канала:  " + publishedAt);
                System.out.println("Кол-во просмотров всех видео:  " + viewCount);
                System.out.println("Кол-во видео:  " + videoCount);
                System.out.println("Название канала: " + item.snippet.localized.title);
            }
        }
    }
}
