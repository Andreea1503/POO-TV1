package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

/**
 * Class that implements the rating of a movie
 */
public class RateAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    private int maximumRating = 5;
    public RateAction(final ActionsInput action, final Page currentPage, final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that implements the rating of a movie
     * First it checks if the movie exists in the current movie list of the user
     * If it does, it checks if the user has watched the movie and if it has and the current page
     * is "movies", "upgrades", "authenticated homepage" or "see details" and the maximum rating
     * a movie can be given doesn't exceed the maximum rating, it adds the rating to the movie,
     * the number of users that rated the movie is increased and the movie is added to the rated
     * movies list of the user, then it outputs a message.
     * Otherwise, an error message is added to the output
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput watchedMovie = MoviesInput.seeDetails(currentPage.getAction(),
                    currentPage.getCurrentUser().getCurrentMoviesList());

            if (watchedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }
            MoviesInput movie = new MoviesInput();
            MoviesInput ratedMovie = movie.isInList(currentPage.getCurrentUser().getWatchedMovies(
            ), watchedMovie.getName());

            if (ratedMovie != null && (currentPage.getCurrentPageName().equals("movies")
                    || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat")
                    || currentPage.getCurrentPageName().equals("see details"))
                    && action.getRate() <= maximumRating) {
                action.setError(null);
                currentPage.getCurrentUser().getRatedMovies().add(ratedMovie);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser()
                        .getRatedMovies().size() - 1).setNumRatings(currentPage.getCurrentUser().
                        getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().
                                size() - 1).getNumRatings() + 1);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().
                        getRatedMovies().size() - 1).getMovieRatings().add(action.getRate());
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
