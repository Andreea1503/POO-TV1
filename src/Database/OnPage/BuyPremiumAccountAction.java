package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class BuyPremiumAccountAction implements OnPageAction {

    public BuyPremiumAccountAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("upgrades") && currentPage.getCurrentUser().getTokensCount() >= 10) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 10);
            currentPage.getCurrentUser().getCredentials().setAccountType("premium");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
