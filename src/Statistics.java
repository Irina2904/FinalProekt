
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Statistics {
    public String subscriberCount;
    public String videoCount;
    public String viewCount;
}
