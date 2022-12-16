package Database.ChangePage;


import Database.*;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

public class ChangePageToLogin implements ChangePageAction {
    public ChangePageToLogin() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            currentPage.setCurrentPageName("login");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
