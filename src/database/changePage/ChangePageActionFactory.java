package database.changePage;

import database.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

/**
 * Factory class used for creating ChangePageAction objects.
 */
public final class ChangePageActionFactory {
    private ChangePageActionFactory() {
    }

    /**
     * Creates a ChangePageAction object that executes the change page action.
     * @param actionType the type of action
     * @param action the action
     * @param currentPage the current page
     * @param movies the list of movies
     * @param output the output
     * @return the ChangePageAction object
     */
    public static ChangePageAction getAction(final String actionType, final ActionsInput action,
                                             final Page currentPage,
                                             final ArrayList<MoviesInput> movies,
                                             final ArrayNode output) {
        switch (actionType) {
            case "login" -> {
                return new ChangePageToLogin(action, currentPage, output);
            }
            case "register" -> {
                return new ChangePageToRegister(action, currentPage, output);
            }
            case "movies" -> {
                return new ChangePageToMovies(action, currentPage, movies, output);
            }
            case "see details" -> {
                return new ChangePageToSeeDetails(action, currentPage, movies, output);
            }
            case "upgrades" -> {
                return new ChangePageToUpgrades(action, currentPage, output);
            }
            case "logout" -> {
                return new ChangePageToLogout(action, currentPage, output);
            }
            default -> {
                return null;
            }
        }
    }
}
