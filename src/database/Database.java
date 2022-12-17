package database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

/**
 * Class that contains the database of the application. It is used to navigate through the pages
 * and to execute the actions. It contains the current page, the current user and the list of
 * current movies.
 * It is also a singleton, because we only need one instance of the database for the entire
 * application.
 */
public final class Database {
    private static volatile Database instance;
    private Page currentPage;

    private Database() {
        currentPage = new Page();
    }

    /**
     * Singleton method that returns the instance of the database.
     * @return the instance of the database
     */
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

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Method that navigates through the pages of the application and executes the type of action
     * that is given as parameter.
     * @param actions the list of actions from the input
     * @param users the list of users from the input
     * @param movies the list of movies from the input
     * @param output the output array node
     */
    public void databaseNavigation(final ArrayList<ActionsInput> actions,
                                   final ArrayList<UsersInput> users,
                                   final ArrayList<MoviesInput> movies,
                                   final ArrayNode output) {
        currentPage.setCurrentPageName("Homepage neautentificat");

        for (ActionsInput action : actions) {
            action.setError(null);
            switch (action.getType()) {
                case "on page" -> currentPage.onPageAction(users, action, currentPage, movies,
                        output);
                case "change page" -> currentPage.changePageAction(action, currentPage, movies,
                        output);
                default -> {
                }
            }
        }
    }
}
