package fileio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionsInput {
    private String feature;
    private String type;
    private String page;
    private CredentialsInput credentials;
    private String error;

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

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
