package ua.goit.java8.project5;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)


public class Snippet {
    public String publishedAt;
    public Localized localized;

}
