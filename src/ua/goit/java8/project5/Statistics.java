package ua.goit.java8.project5;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Statistics {
    public String subscriberCount;
    public String videoCount;
    public String viewCount;
}
