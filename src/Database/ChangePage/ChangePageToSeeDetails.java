package Database.ChangePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import Database.Page;
import Write.Write;

import java.util.ArrayList;

public class ChangePageToSeeDetails implements ChangePageAction {
    public ChangePageToSeeDetails() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("see details")) {
            currentPage.setCurrentPageName("see details");
            MoviesInput allowedMovie = MoviesInput.seeDetails(action, currentPage.getCurrentUser().getCurrentMoviesList());

            if (allowedMovie != null) {
                currentPage.getCurrentUser().setCurrentMoviesList(new ArrayList<>());
                currentPage.setAction(action);
                currentPage.getCurrentUser().getCurrentMoviesList().add(allowedMovie);
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                currentPage.setAction(action);
                MoviesInput movie = new MoviesInput();
                currentPage.getCurrentUser().setCurrentMoviesList(movie.allowedMoviesForASpecificUser(movies, currentPage.getCurrentUser()));
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            currentPage.setAction(action);
            MoviesInput movie = new MoviesInput();
            currentPage.getCurrentUser().setCurrentMoviesList(movie.allowedMoviesForASpecificUser(movies, currentPage.getCurrentUser()));
            Write.writePageError(null, action, output);
        }
    }
}
