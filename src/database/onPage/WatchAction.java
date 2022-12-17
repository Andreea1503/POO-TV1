package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

/**
 * Class that implements the watch action
 */
public class WatchAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public WatchAction(final ActionsInput action, final Page currentPage, final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that implents the watch action
     * If the movie is in the list of movies for the user, it checks if the user has already bought
     * it. If he has and the current page is "movies", "authenticated homepage" or "see details",
     * then the films is added to the list of watched movies of the user.
     * Otherwise, an error message is added to the output.
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput purchasedMovie = MoviesInput.seeDetails(currentPage.getAction(),
                    currentPage.getCurrentUser().getCurrentMoviesList());

            if (purchasedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            MoviesInput movie = new MoviesInput();
            MoviesInput watchedMovie = movie.isInList(currentPage.getCurrentUser().
                    getPurchasedMovies(), purchasedMovie.getName());

            if (watchedMovie != null && (currentPage.getCurrentPageName().equals("movies")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat")
                    || currentPage.getCurrentPageName().equals("see details"))) {
                currentPage.getCurrentUser().getWatchedMovies().add(watchedMovie);
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
