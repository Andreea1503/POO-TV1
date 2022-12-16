package Database.ChangePage;


import Database.*;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

public class ChangePageToLogout implements ChangePageAction {
    public ChangePageToLogout() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("login") || currentPage.getCurrentPageName().equals("register")
                || currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            action.setError("Error");
            Write.writePageError(null, action, output);
        } else {
            currentPage.setCurrentPageName("Homepage neautentificat");
            currentPage.setCurrentUser(null);
        }
    }
}
