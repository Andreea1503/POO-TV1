package database.onPage;

import database.Page;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

/**
 * Class that creates the on page action objects
 */
public final class OnPageActionFactory {
    private OnPageActionFactory() {
    }

    /**
     * Method that creates the on page action objects
     * @param actionType the type of the action
     * @param users the list of users
     * @param action the action
     * @param currentPage the current page
     * @param movies the list of movies
     * @param output the output
     * @return the on page action object
     */
    public static OnPageAction getAction(final String actionType,
                                         final ArrayList<UsersInput> users,
                                         final ActionsInput action, final Page currentPage,
                                         final ArrayList<MoviesInput> movies,
                                         final ArrayNode output) {
        switch (actionType) {
            case "login" -> {
                return new LoginAction(users, action, currentPage, output);
            }
            case "register" -> {
                return new RegisterAction(users, action, currentPage, output);
            }
            case "search" -> {
                return new SearchAction(action, currentPage, output);
            }
            case "filter" -> {
                return new FilterAction(action, currentPage, movies, output);
            }
            case "buy tokens" -> {
                return new BuyTokensAction(action, currentPage, output);
            }
            case "buy premium account" -> {
                return new BuyPremiumAccountAction(action, currentPage, output);
            }
            case "purchase" -> {
                return new PurchaseAction(action, currentPage, output);
            }
            case "watch" -> {
                return new WatchAction(action, currentPage, output);
            }
            case "like" -> {
                return new LikeAction(action, currentPage, output);
            }
            case "rate" -> {
                return new RateAction(action, currentPage, output);
            }
            default -> {
                return null;
            }
        }
    }
}
