package ua.goit.java8.project5;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResponse {
    public List<Activity> items;
    public Activity items2;
}