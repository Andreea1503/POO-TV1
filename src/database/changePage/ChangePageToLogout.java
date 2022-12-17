package database.changePage;


import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

/**
 * Class that executes the logout command
 */
public class ChangePageToLogout implements ChangePageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public ChangePageToLogout(final ActionsInput action, final Page currentPage,
                              final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that return the action
     * @return action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that return the current page
     * @return currentPage
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Method that return the output
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that executes the logout command if the user is logged in
     * or outputs an error message if the user is not logged in
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("login")
                || currentPage.getCurrentPageName().equals("register")
                || currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            action.setError("Error");
            Write.writePageError(null, action, output);
        } else {
            currentPage.setCurrentPageName("Homepage neautentificat");
            currentPage.setCurrentUser(null);
        }
    }
}
