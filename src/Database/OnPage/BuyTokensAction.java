package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class BuyTokensAction implements OnPageAction{

    public BuyTokensAction() {
    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("upgrades")) {
            int balance = Integer.valueOf(currentPage.getCurrentUser().getCredentials().getBalance());
            balance -= Integer.valueOf(action.getCount());
            currentPage.getCurrentUser().getCredentials().setBalance(String.valueOf(balance));
            currentPage.getCurrentUser().setTokensCount(Integer.valueOf(action.getCount()));
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
