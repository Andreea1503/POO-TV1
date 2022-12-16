package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class WatchAction implements OnPageAction{

    public WatchAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput purchasedMovie = MoviesInput.seeDetails(currentPage.getAction(), currentPage.getCurrentUser().getCurrentMoviesList());

            if (purchasedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            MoviesInput movie = new MoviesInput();
            MoviesInput watchedMovie = movie.isInList(currentPage.getCurrentUser().getPurchasedMovies(), purchasedMovie.getName());

            if (watchedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details"))) {
                currentPage.getCurrentUser().getWatchedMovies().add(watchedMovie);
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
