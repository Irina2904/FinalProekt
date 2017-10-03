package ua.goit.java8.project5;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)


public class Snippet {
    public Date publishedAt;
    public Localized localized;

}
