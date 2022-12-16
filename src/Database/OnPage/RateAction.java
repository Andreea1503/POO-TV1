package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class RateAction implements OnPageAction {

    public RateAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput watchedMovie = MoviesInput.seeDetails(currentPage.getAction(), currentPage.getCurrentUser().getCurrentMoviesList());

            if (watchedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }
            MoviesInput movie = new MoviesInput();
            MoviesInput ratedMovie = movie.isInList(currentPage.getCurrentUser().getWatchedMovies(), watchedMovie.getName());

            if (ratedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details")) && action.getRate() <= 5) {
                action.setError(null);
                currentPage.getCurrentUser().getRatedMovies().add(ratedMovie);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).setNumRatings(currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).getNumRatings() + 1);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).getMovieRatings().add(action.getRate());
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
