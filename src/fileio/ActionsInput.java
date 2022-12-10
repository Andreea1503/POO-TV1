package fileio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionsInput {
    private String feature;
    private String type;
    private String page;
    private UserCredentials credentials;

    public ActionsInput() {
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "ActionsInput{"
                + "command='"
                + feature
                + '\''
                + ", type='"
                + type
                + '\''
                + ", page='"
                + page
                + '\''
                + ", credentials="
                + credentials
                + '}';
    }
}
