package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.UsersInput;

import java.util.ArrayList;

/**
 * Class that registers a user
 */
public class RegisterAction implements OnPageAction {
    private ArrayList<UsersInput> users;
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    public RegisterAction(final ArrayList<UsersInput> users, final ActionsInput action,
                          final Page currentPage, final ArrayNode output) {
        this.users = users;
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that registers a user
     * If the current page is registered, then it is verified that the user is not already
     * registered, and if it is not, then it is added to the list of users, and logged in, the
     * current page is updated to "authenticated homepage".
     * Otherwise, an error message is displayed.
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("register")) {
            for (UsersInput user : users) {
                if (user.getCredentials().equals(action.getCredentials())) {
                    currentPage.setCurrentPageName("Homepage neautentificat");
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                    return;
                }
            }
            users.add(new UsersInput(action.getCredentials()));
            currentPage.setCurrentUser(users.get(users.size() - 1));

            currentPage.setCurrentPageName("Homepage autentificat");
            Write.writePageError(currentPage.getCurrentUser(),  action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentPageName("Homepage neautentificat");
            Write.writePageError(null, action, output);
        }
    }
}
