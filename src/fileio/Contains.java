package fileio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Class that contains the input for the filter command
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public Contains() {
    }

    /**
     * Methos that returns the actors
     * @return actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Method that sets the actors
     * @param actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Method that returns the genre
     * @return genre
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     * Method that sets the genre
     * @param genre
     */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
