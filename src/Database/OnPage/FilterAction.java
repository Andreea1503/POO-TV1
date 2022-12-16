package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class FilterAction implements OnPageAction {
    public FilterAction() {

    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("see details")) {
            MoviesInput movie = new MoviesInput();
            ArrayList<MoviesInput> filteredMovies = movie.filter(movies, currentPage.getCurrentUser(), action);
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
