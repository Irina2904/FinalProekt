package ua.goit.java8.project5;/*Сортировать каналы по их данным
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
import java.util.Arrays;
import java.util.Scanner;

public class TaskSorting {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;


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

    public static void main(String[] args) throws UnirestException, NullPointerException, Exception {

        System.out.println("Введите массив ID Каналов через пробел");
        Scanner sc = new Scanner(System.in);

        String[] channelID = sc.nextLine().split(" ");
        initApplication();

        for (int i1 = 0; i1 < channelID.length; i1++) {

            HttpResponse<ActivityResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                    .queryString("part", "snippet,statistics")
                    .queryString("id", channelID[i1])
                    .queryString("key", "AIzaSyAoJtBYFKQT3rF5Qy0P0hHEFdAMDnOXOhA")
                    .asObject(ActivityResponse.class);

            ActivityResponse activity = response.getBody();

            for (int i = 0; i < activity.items.size(); i++) {

                final Activity item = activity.items.get(i);

                String subscriberCount2 = item.statistics.subscriberCount;
                String videoCount = item.statistics.videoCount;
                String publishedAt = item.snippet.publishedAt;
                String viewCount = item.statistics.viewCount;
                String title =  item.snippet.localized.title;

                for (int i2 = 0; i2 < activity.items.size(); i2++) {
                    String[] subscriberCount3 = new String[activity.items.size()];
                    subscriberCount3[i] = subscriberCount2;
                    sortingByViewCount(subscriberCount3);
                    System.out.println(Arrays.asList(subscriberCount3));
                }

                System.out.println("Кол-во подписчиков: " + subscriberCount2);
                System.out.println("Дата создания канала:  " + publishedAt);
                System.out.println("Кол-во просмотров всех видео:  " + viewCount);
                System.out.println("Кол-во видео:  " + videoCount);
                System.out.println("Название канала: " + title);
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
