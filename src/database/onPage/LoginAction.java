package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.UsersInput;

import java.util.ArrayList;

/**
 * Class that implements the login action
 */
public class LoginAction implements OnPageAction {
    private ArrayList<UsersInput> users;
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public LoginAction(final ArrayList<UsersInput> users, final ActionsInput action,
                       final Page currentPage, final ArrayNode output) {
        this.users = users;
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that executes the login action if the current page is the login page, then it
     * checks if the username and password are in the database, if they are, then the user is
     * logged in, the current page is set to "authenticated homepage", the current user is set
     * to the user that logged in and the output is written
     * Otherwise, an error message is written
     */
    @Override
    public void execute() {
        boolean found = false;
        if (currentPage.getCurrentPageName().equals("login")) {
            for (UsersInput user : users) {
                if (user.getCredentials().getName().equals(action.getCredentials().getName())
                        && user.getCredentials().getPassword().equals(
                                action.getCredentials().getPassword())) {
                    currentPage.setCurrentPageName("Homepage autentificat");
                    currentPage.setCurrentUser(new UsersInput(user.getCredentials()));
                    found = true;
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                }
            }

            if (!found) {
                action.setError("Error");
                currentPage.setCurrentPageName("Homepage neautentificat");
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
