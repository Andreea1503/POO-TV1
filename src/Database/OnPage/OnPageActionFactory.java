package Database.OnPage;

public class OnPageActionFactory {
    public static OnPageAction getAction(String actionType) {
        switch (actionType) {
            case "login" -> {
                return new LoginAction();
            }
            case "register" -> {
                return new RegisterAction();
            }
            case "search" -> {
                return new SearchAction();
            }
            case "filter" -> {
                return new FilterAction();
            }
            case "buy tokens" -> {
                return new BuyTokensAction();
            }
            case "buy premium account" -> {
                return new BuyPremiumAccountAction();
            }
            case "purchase" -> {
                return new PurchaseAction();
            }
            case "watch" -> {
                return new WatchAction();
            }
            case "like" -> {
                return new LikeAction();
            }
            case "rate" -> {
                return new RateAction();
            }
            default -> {
                return null;
            }
        }
    }
}
