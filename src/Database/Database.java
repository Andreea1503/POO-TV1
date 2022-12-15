package Database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class Database {
    private static volatile Database instance;
    Page currentPage;

    private Database() {
        currentPage = new Page();
    }

    public static Database getInstance() {
        Database result = instance;
        if (result == null) {
            synchronized (Database.class) {
                result = instance;
                if (result == null) {
                    instance = result = new Database();
                }
            }
        }
        return result;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void databaseNavigation(ArrayList<ActionsInput> actions, ArrayList<UsersInput> users, ArrayList<MoviesInput> movies, ArrayNode output) {
        currentPage.setCurrentPageName("Homepage neautentificat");

        for (ActionsInput action : actions) {
            action.setError(null);
            switch(action.getType()) {
                case "on page" -> currentPage.onPageAction(users, action, currentPage, movies, output);
                case "change page" -> currentPage.changePageAction(action, currentPage, movies, output);
            }
        }
    }

}