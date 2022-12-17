package database.changePage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

/**
 * Class that executes the change page to upgrades command
 */
public class ChangePageToUpgrades implements ChangePageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    public ChangePageToUpgrades(final ActionsInput action, final Page currentPage,
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
     * Method that changes the current page to the upgrades page.
     * If the command is successful, the current page is changed to the upgrades page.
     * Otherwise, the current page remains the same and an error message is added to the output.
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("see details")
                || currentPage.getCurrentPageName().equals("Homepage autentificat")) {
            currentPage.setCurrentPageName("upgrades");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
