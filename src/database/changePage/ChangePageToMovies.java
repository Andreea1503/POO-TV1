package database.changePage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

/**
 * Class that executes the change page to movies command
 */
public class ChangePageToMovies implements ChangePageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayList<MoviesInput> movies;
    private ArrayNode output;
    public ChangePageToMovies(final ActionsInput action, final Page currentPage,
                              final ArrayList<MoviesInput> movies, final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.movies = movies;
        this.output = output;
    }

    /**
     * Method that returns the action
     * @return action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that returns the current page
     * @return currentPage
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Method that returns the movies
     * @return movies
     */
    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    /**
     * Method that returns the output
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that changes the page to movies if the current page is "authenticated homepage",
     * "see details" or "upgrades" or outputs the error message.
     * If the change page is successful, the current movie list for the current user is updated
     * with the films that are allowed in his country and the result is written in the output file.
     */
    @Override
    public void execute() {
        if ((currentPage.getCurrentPageName().equals("Homepage autentificat")
                || currentPage.getCurrentPageName().equals("see details")
                || currentPage.getCurrentPageName().equals("upgrades"))
                && currentPage.getCurrentUser() != null) {
            currentPage.setCurrentPageName("movies");
            MoviesInput movie = new MoviesInput();
            currentPage.getCurrentUser().setCurrentMoviesList(
                    movie.allowedMoviesForUser(movies, currentPage.getCurrentUser()));
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
