package Database.ChangePage;


import Database.*;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

public class ChangePageToRegister implements ChangePageAction {
    public ChangePageToRegister() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            currentPage.setCurrentPageName("register");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
