package Database;

import Database.ChangePage.ChangePageAction;
import Database.ChangePage.ChangePageActionFactory;
import Database.OnPage.OnPageAction;
import Database.OnPage.OnPageActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;

import Write.*;
import fileio.*;
public class Page {
    private String currentPageName;
    private UsersInput currentUser;
    private ActionsInput action;
    public Page() {
    }

    public String getCurrentPageName() {
        return currentPageName;
    }

    public void setCurrentPageName(String currentPageName) {
        this.currentPageName = currentPageName;
    }

    public UsersInput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsersInput currentUser) {
        this.currentUser = currentUser;
    }

    public ActionsInput getAction() {
        return action;
    }

    public void setAction(ActionsInput action) {
        this.action = action;
    }


    public void onPageAction(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        OnPageAction onPageAction = OnPageActionFactory.getAction(action.getFeature());
        onPageAction.execute(users, action, currentPage, movies, output);
    }

    public void changePageAction(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        ChangePageAction changePageAction = ChangePageActionFactory.getAction(action.getPage());
        changePageAction.execute(action, currentPage, movies, output);
    }
}