package Database.ChangePage;
import Database.*;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class ChangePageToMovies implements ChangePageAction {
    public ChangePageToMovies() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if ((currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details")
                || currentPage.getCurrentPageName().equals("upgrades")) && currentPage.getCurrentUser() != null) {
            currentPage.setCurrentPageName("movies");
            MoviesInput movie = new MoviesInput();
            currentPage.getCurrentUser().setCurrentMoviesList(movie.allowedMoviesForASpecificUser(movies, currentPage.getCurrentUser()));
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
