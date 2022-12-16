package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class LoginAction implements OnPageAction {
    public LoginAction() {

    }
    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        boolean found = false;
        if (currentPage.getCurrentPageName().equals("login")) {
            for (UsersInput user : users) {
                if (user.getCredentials().getName().equals(action.getCredentials().getName()) && user.getCredentials().getPassword().equals(action.getCredentials().getPassword())) {
                    currentPage.setCurrentPageName("Homepage autentificat");
                    currentPage.setCurrentUser(new UsersInput(user.getCredentials()));
                    found = true;
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                    return;
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
