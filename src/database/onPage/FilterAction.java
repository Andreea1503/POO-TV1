package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

/**
 * Class that executes the filter movies command
 */
public class FilterAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayList<MoviesInput> movies;
    private ArrayNode output;
    public FilterAction(final ActionsInput action, final Page currentPage,
                        final ArrayList<MoviesInput> movies, final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.movies = movies;
        this.output = output;
    }

    /**
     * Method that executes the filter movies command if the current page is movies or see details
     * If the command is successful, the movies are filtered and if the list of filtered movies is
     * not empty, the output is written
     * Otherwise, an error message is written in the output file
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("movies")
                || currentPage.getCurrentPageName().equals("see details")) {
            MoviesInput movie = new MoviesInput();
            ArrayList<MoviesInput> filteredMovies = movie.filter(movies,
                    currentPage.getCurrentUser(), action);
            if (filteredMovies != null) {
                currentPage.getCurrentUser().setCurrentMoviesList(filteredMovies);
            }
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
