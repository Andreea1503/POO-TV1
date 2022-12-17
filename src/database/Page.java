package database;

import database.changePage.ChangePageAction;
import database.changePage.ChangePageActionFactory;
import database.onPage.OnPageAction;
import database.onPage.OnPageActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;

import fileio.UsersInput;
import fileio.ActionsInput;
import fileio.MoviesInput;

/**
 * Class that contains a page of the application and executes the proper action. It contains the
 * current page name, the current user and the list of current movies.
 */
public class Page {
    private String currentPageName;
    private UsersInput currentUser;
    private ActionsInput action;

    public Page() {
    }

    /**
     * Method that returns the current page name.
     * @return the current page name
     */
    public String getCurrentPageName() {
        return currentPageName;
    }

    /**
     * Method that sets the current page name.
     * @param currentPageName the current page name
     */
    public void setCurrentPageName(final String currentPageName) {
        this.currentPageName = currentPageName;
    }

    /**
     * Method that returns the current user.
     * @return the current user
     */
    public UsersInput getCurrentUser() {
        return currentUser;
    }

    /**
     * Method that sets the current user.
     * @param currentUser the current user
     */
    public void setCurrentUser(final UsersInput currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Method that returns the current action.
     * @return the current action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that sets the current action.
     * @param action the current action
     */
    public void setAction(final ActionsInput action) {
        this.action = action;
    }

    /**
     * Method that executes the "on page" action using a factory design pattern.
     * @param users the list of users
     * @param action the action to be executed
     * @param currentPage the current page
     * @param movies the list of movies
     * @param output the output array node
     */
    public void onPageAction(final ArrayList<UsersInput> users, final ActionsInput action,
                             final Page currentPage, final ArrayList<MoviesInput> movies,
                             final ArrayNode output) {
        OnPageAction onPageAction = OnPageActionFactory.
                getAction(action.getFeature(), users, action, currentPage, movies, output);
        onPageAction.execute();
    }

    /**
     * Method that executes the "change page" action using a factory design pattern.
     * @param action the action to be executed
     * @param currentPage the current page
     * @param movies the list of movies
     * @param output the output array node
     */
    public void changePageAction(final ActionsInput action, final Page currentPage,
                                 final ArrayList<MoviesInput> movies, final ArrayNode output) {
        ChangePageAction changePageAction = ChangePageActionFactory.
                getAction(action.getPage(), action, currentPage, movies, output);
        changePageAction.execute();
    }
}
