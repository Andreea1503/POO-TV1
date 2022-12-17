package database.changePage;


import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

/**
 * Class that executes the register command
 */
public class ChangePageToRegister implements ChangePageAction {

    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    public ChangePageToRegister(final ActionsInput action, final Page currentPage,
                                final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that returns the action
     * @return action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that returns the current page
     * @return currentPage
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Method that returns the output
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that changes the page to register if the current page is "unauthenticated homepage",
     * otherwise it writes an error message in the output file
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            currentPage.setCurrentPageName("register");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
