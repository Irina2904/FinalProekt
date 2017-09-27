
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)


public class Snippet {
    public String publishedAt;
    public Localized localized;

}
