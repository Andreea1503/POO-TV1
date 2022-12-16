package Database.OnPage;

import Database.Page;
import Write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;

public class RegisterAction implements OnPageAction{
    public RegisterAction() {

    }

    @Override
    public void execute(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
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
