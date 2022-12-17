package database.onPage;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;

/**
 * Class that implements the action of buying a premium account
 */
public class BuyPremiumAccountAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    private int cost = 10;

    public BuyPremiumAccountAction(final ActionsInput action, final Page currentPage,
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
     * Method that checks if the current page is upgrades and if the user has enough tokens to buy
     * a premium account
     * If the user has enough tokens, the type of the account is changed to "premium" and the
     * tokens are subtracted from the user
     * If the user doesn't have enough tokens, the type of the account is not changed and an error
     * message is added to the output
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("upgrades")
                && currentPage.getCurrentUser().getTokensCount() >= cost) {
            currentPage.getCurrentUser().setTokensCount(
                    currentPage.getCurrentUser().getTokensCount() - cost);
            currentPage.getCurrentUser().getCredentials().setAccountType("premium");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
