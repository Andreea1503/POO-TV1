package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class PurchaseAction implements OnPageAction{

    public PurchaseAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        MoviesInput purchasedMovies = purchaseMovieOnAccount(action, currentPage, output);
        if (purchasedMovies != null && currentPage.getCurrentPageName().equals("see details")) {
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    public MoviesInput purchaseMovieOnAccount(ActionsInput action, Page currentPage, ArrayNode output) {
        MoviesInput purchasedMovie = MoviesInput.seeDetails(currentPage.getAction(), currentPage.getCurrentUser().getCurrentMoviesList());
        if (purchasedMovie != null && currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium") && currentPage.getCurrentUser().getNumFreePremiumVideos() > 0) {
            currentPage.getCurrentUser().setNumFreePremiumVideos(currentPage.getCurrentUser().getNumFreePremiumVideos() - 1);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
        } else if (purchasedMovie != null && (currentPage.getCurrentUser().getCredentials().getAccountType().equals("standard") || currentPage.getCurrentUser().getNumFreePremiumVideos() == 0)) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 2);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
        }
        return purchasedMovie;
    }
}
