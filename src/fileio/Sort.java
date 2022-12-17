package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that contains the input for the filter command
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sort {
    private String rating;
    private String duration;

    public Sort() {
    }

    /**
     * Method that return the rating of the movie
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Method that set the rating of the movie
     * @param rating
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * Method that return the duration of the movie
     * @return duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Method that set the duration of the movie
     * @param duration
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
