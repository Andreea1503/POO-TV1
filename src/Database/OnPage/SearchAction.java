package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class SearchAction implements OnPageAction {

    public SearchAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("movies")) {
            currentPage.getCurrentUser().setCurrentMoviesList(MoviesInput.searchMovie(action, currentPage.getCurrentUser()));
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
