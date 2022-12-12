package Database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;
import Write.*;
public class Page {

    private String currentPageName;

    public Page() {

    }

    public Page(String currentPageName) {
        this.currentPageName = currentPageName;
    }

    public String getCurrentPageName() {
        return currentPageName;
    }

    public void setCurrentPageName(String currentPageName) {
        this.currentPageName = currentPageName;
    }

    public void onPageAction(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, UsersInput currentUser, ArrayNode output) {
        switch (action.getFeature()) {
            case "login" -> {
                logIntoTheDatabase(users, action, currentPage, currentUser, output);
            }
            case "register" -> {
                registerIntoTheDatabase(users, action, currentPage, currentUser, output);
            }
        }
    }

    public void changePageAction(ActionsInput action, Page currentPage, UsersInput currentUser, ArrayNode output) {
        switch (action.getPage()) {
            case "login" -> {
                if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    currentPage.setCurrentPageName("login");
                } else {
                    action.setError("Error");
                    Write.writePageError(currentUser, action, output);
                }
            }
            case "register" -> {
                if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    currentPage.setCurrentPageName("register");
                } else {
                    action.setError("Error");
                    Write.writePageError(currentUser, action, output);
                }
            }
            case "movies" -> {
                if (currentPage.getCurrentPageName().equals("Homepage autentificat")) {
                    currentPage.setCurrentPageName("movies");
                } else {
                    action.setError("Error");
                    Write.writePageError(currentUser, action, output);
                }
            }
            case "logout" -> {
                currentPage.setCurrentPageName("Homepage neautentificat");
            }

        }
    }

    public void logIntoTheDatabase(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, UsersInput currentUser, ArrayNode output) {
        boolean found = false;
        if (currentPage.getCurrentPageName().equals("login")) {
            for (UsersInput user : users) {
                if (user.getCredentials().getName().equals(action.getCredentials().getName()) && user.getCredentials().getPassword().equals(action.getCredentials().getPassword())) {
                    action.setError(null);
                    currentPage.setCurrentPageName("Homepage autentificat");
                    currentUser = new UsersInput(user.getCredentials());
                    found = true;
                    Write.writePageError(currentUser, action, output);
                    return;
                }
            }

            if (!found) {
                action.setError("Error");
                currentPage.setCurrentPageName("Homepage neautentificat");
                Write.writePageError(currentUser, action, output);
            }
        } else {
            action.setError("Error");
            currentPage.setCurrentPageName("Homepage neautentificat");
            Write.writePageError(currentUser, action, output);
        }
    }

    public void registerIntoTheDatabase(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, UsersInput currentUser, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("register")) {
            for (UsersInput user : users) {
                if (user.getCredentials().equals(action.getCredentials())) {
                    currentPage.setCurrentPageName("Homepage neautentificat");
                    action.setError("Error");
                    Write.writePageError(currentUser, action, output);
                    return;
                }
            }
            users.add(new UsersInput(action.getCredentials()));
            currentUser = users.get(users.size() - 1);

            currentPage.setCurrentPageName("Homepage autentificat");
            action.setError(null);
            Write.writePageError(currentUser,  action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentPageName("Homepage neautentificat");
            Write.writePageError(currentUser, action, output);
        }
    }
}
