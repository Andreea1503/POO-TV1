package Database.ChangePage;

public class ChangePageActionFactory {
    public static ChangePageAction getAction(String actionType) {
        switch (actionType) {
            case "login" -> {
                return new ChangePageToLogin();
            }
            case "register" -> {
                return new ChangePageToRegister();
            }
            case "movies" -> {
                return new ChangePageToMovies();
            }
            case "see details" -> {
                return new ChangePageToSeeDetails();
            }
            case "upgrades" -> {
                return new ChangePageToUpgrades();
            }
            case "logout" -> {
                return new ChangePageToLogout();
            }
            default -> {
                return null;
            }
        }
    }
}
