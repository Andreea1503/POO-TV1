package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

/**
 * Class that implements the buy tokens action
 */
public class BuyTokensAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public BuyTokensAction(final ActionsInput action, final Page currentPage,
                           final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that returns the action
     * @return the action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that returns the current page
     * @return the current page
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Method that returns the output
     * @return the output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that executes the buy tokens action if the current page is upgrades, otherwise it
     * returns an error message
     * If the command is valid, then the user balance and tokens are updated
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("upgrades")) {
            int balance = Integer.valueOf(currentPage.getCurrentUser().getCredentials(
            ).getBalance());
            balance -= Integer.valueOf(action.getCount());
            currentPage.getCurrentUser().getCredentials().setBalance(String.valueOf(balance));
            currentPage.getCurrentUser().setTokensCount(Integer.valueOf(action.getCount()));
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
