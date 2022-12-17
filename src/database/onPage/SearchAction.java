package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

/**
 * Class that implements the search action
 */
public class SearchAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    public SearchAction(final ActionsInput action, final Page currentPage,
                        final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that searches for a movie in the database
     * If the current page is movie type, it the movie is searched and an output is displayed
     * Otherwise, an error message is displayed
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("movies")) {
            currentPage.getCurrentUser().setCurrentMoviesList(MoviesInput.
                    searchMovie(action, currentPage.getCurrentUser()));
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
