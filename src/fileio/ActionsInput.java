package fileio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that contains the input for the actions
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionsInput {
    private String feature;
    private String type;
    private String page;
    private CredentialsInput credentials;
    private String error;
    private String startsWith;
    private String movie;
    private Filters filters;
    private String objectType;
    private String count;
    private int rate;

    public ActionsInput() {
    }

    /**
     * Method that returns the feature
     * @return feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Method that sets the feature
     * @param feature
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * Method that returns the type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Method that sets the type
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Method that returns the page
     * @return page
     */
    public String getPage() {
        return page;
    }

    /**
     * Method that sets the page
     * @param page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * Method that returns the credentials
     * @return credentials
     */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /**
     * Method that sets the credentials
     * @param credentials
     */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    /**
     * Method that returns the error
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     * Method that sets the error
     * @param error
     */
    public void setError(final String error) {
        this.error = error;
    }

    /**
     * Method that returns the startsWith
     * @return startsWith
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Method that sets the startsWith
     * @param startsWith
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * Method that returns the movie
     * @return movie
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * Method that sets the movie
     * @param filters
     */
    public void setFilters(final Filters filters) {
        this.filters = filters;
    }

    /**
     * Method that returns the objectType
     * @return objectType
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Method that sets the objectType
     * @param movie
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * Method that returns the objectType
     * @return objectType
     */
    public String getCount() {
        return count;
    }

    /**
     * Method that sets the objectType
     * @param count
     */
    public void setCount(final String count) {
        this.count = count;
    }

    /**
     * Method that returns the objectType
     * @return objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Method that sets the objectType
     * @param objectType
     */
    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    /**
     * Method that returns the rate
     * @return rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Method that sets the rate
     * @param rate
     */
    public void setRate(final int rate) {
        this.rate = rate;
    }
}
