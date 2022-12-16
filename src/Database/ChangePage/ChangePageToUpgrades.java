package Database.ChangePage;

import Database.*;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

public class ChangePageToUpgrades implements ChangePageAction {
    public ChangePageToUpgrades() {
    }

    @Override
    public void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("see details") || currentPage.getCurrentPageName().equals("Homepage autentificat")) {
            currentPage.setCurrentPageName("upgrades");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
