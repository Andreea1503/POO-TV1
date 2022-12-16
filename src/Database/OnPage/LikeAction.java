package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class LikeAction implements OnPageAction {

    public LikeAction() {
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
            MoviesInput likedMovie = movie.isInList(currentPage.getCurrentUser().getWatchedMovies(), watchedMovie.getName());

            if (likedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details"))) {
                currentPage.getCurrentUser().getLikedMovies().add(likedMovie);
                currentPage.getCurrentUser().getLikedMovies().get(currentPage.getCurrentUser().getLikedMovies().size() - 1).setNumLikes(currentPage.getCurrentUser().getLikedMovies().get(currentPage.getCurrentUser().getLikedMovies().size() - 1).getNumLikes() + 1);
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
