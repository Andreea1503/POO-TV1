package Database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import Write.*;
import fileio.*;
public class Page {

    private String currentPageName;
    private UsersInput currentUser;

    public Page() {

    }

    public Page(String currentPageName) {
        this.currentPageName = currentPageName;
        this.currentUser = null;
    }

    public String getCurrentPageName() {
        return currentPageName;
    }

    public void setCurrentPageName(String currentPageName) {
        this.currentPageName = currentPageName;
    }

    public UsersInput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UsersInput currentUser) {
        this.currentUser = currentUser;
    }

    public void onPageAction(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        switch (action.getFeature()) {
            case "login" -> {
                logIntoTheDatabase(users, action, currentPage, output);
            }
            case "register" -> {
                registerIntoTheDatabase(users, action, currentPage, output);
            }
            case "search" -> {
                searchIntoTheDatabase(action, currentPage, output, movies);
            }
            case "filter" -> {
                filterIntoTheDatabase(action, currentPage, output, movies);
            }
            case "buy tokens" -> {
                buyTokens(action, currentPage, output);
            }
            case "buy premium account" -> {
                buyPremiumAccount(action, currentPage, output);
            }
            case "purchase" -> {
                purchase(action, currentPage, output, movies);
            }
            case "watch" -> {
                watch(action, currentPage, output, movies);
            }
        }
    }

    public void changePageAction(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output) {
        switch (action.getPage()) {
            case "login" -> {
                if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    currentPage.setCurrentPageName("login");
                } else {
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                }
            }
            case "register" -> {
                if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    currentPage.setCurrentPageName("register");
                } else {
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writeMoviesError(movies, action, currentPage.getCurrentUser(), output);
                }
            }
            case "movies" -> {
                if (currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details")) {
                    currentPage.setCurrentPageName("movies");
                    action.setError(null);
                    Write.writeMoviesError(movies, action, currentPage.getCurrentUser(), output);
                } else {
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                }
            }
            case "see details" -> {
                if (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("see details")) {
                    currentPage.setCurrentPageName("see details");
                    MoviesInput movie = seeDetails(action, currentPage, output, movies);
                    if (movie != null) {
                        Write.writeFilmDetails(currentPage.getCurrentUser(), movie, action, output);
                    } else {
                        action.setError("Error");
                        currentPage.setCurrentUser(null);
                        Write.writePageError(currentPage.getCurrentUser(), action, output);
                    }
                } else {
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                }
            }
            case "upgrades" -> {
                if (currentPage.getCurrentPageName().equals("see details")) {
                    currentPage.setCurrentPageName("upgrades");
                } else {
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                }
            }
            case "logout" -> {
                currentPage.setCurrentPageName("Homepage neautentificat");
                currentPage.setCurrentUser(null);
            }

        }
    }

    public void logIntoTheDatabase(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayNode output) {
        boolean found = false;
        if (currentPage.getCurrentPageName().equals("login")) {
            for (UsersInput user : users) {
                if (user.getCredentials().getName().equals(action.getCredentials().getName()) && user.getCredentials().getPassword().equals(action.getCredentials().getPassword())) {
                    action.setError(null);
                    currentPage.setCurrentPageName("Homepage autentificat");
                    currentPage.setCurrentUser(new UsersInput(user.getCredentials()));
                    found = true;
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                    return;
                }
            }

            if (!found) {
                action.setError("Error");
                currentPage.setCurrentUser(null);
                currentPage.setCurrentPageName("Homepage neautentificat");
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            }
        } else {
            action.setError("Error");
            currentPage.setCurrentPageName("Homepage neautentificat");
            currentPage.setCurrentUser(null);
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }

    public void registerIntoTheDatabase(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("register")) {
            for (UsersInput user : users) {
                if (user.getCredentials().equals(action.getCredentials())) {
                    currentPage.setCurrentPageName("Homepage neautentificat");
                    action.setError("Error");
                    currentPage.setCurrentUser(null);
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                    return;
                }
            }
            users.add(new UsersInput(action.getCredentials()));
            currentPage.setCurrentUser(users.get(users.size() - 1));

            currentPage.setCurrentPageName("Homepage autentificat");
            action.setError(null);
            Write.writePageError(currentPage.getCurrentUser(),  action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            currentPage.setCurrentPageName("Homepage neautentificat");
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }

    public void searchIntoTheDatabase(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentPageName().equals("movies")) {
            action.setError(null);
            Write.writeSearchError(movies, action, currentPage.getCurrentUser(), output);
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            Write.writeSearchError(movies,  action, currentPage.getCurrentUser(), output);
        }
    }

    public void filterIntoTheDatabase(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentPageName().equals("movies")) {
            action.setError(null);
            MoviesInput movie = new MoviesInput();
            ArrayList<MoviesInput> filteredMovies = movie.filer(movies, currentPage.getCurrentUser(), action);
            Write.writeFilter(filteredMovies, currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }

    public MoviesInput seeDetails(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        for (MoviesInput movie : movies) {
            if (action.getMovie().equals(movie)) {
                return movie;
            }
        }
        return null;
    }

    // mai scriem si noi cu write?
    public void buyTokens(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("upgrades")) {
            int balance = Integer.valueOf(currentPage.getCurrentUser().getCredentials().getBalance());
            balance -= Integer.valueOf(action.getCount());
            currentPage.getCurrentUser().getCredentials().setBalance(String.valueOf(balance));
            currentPage.getCurrentUser().setTokensCount(Integer.valueOf(action.getCount()));
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }

    public void buyPremiumAccount(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("upgrades") && currentPage.getCurrentUser().getTokensCount() >= 10) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 10);
            currentPage.getCurrentUser().getCredentials().setAccountType("premium");
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }

    public void purchase(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentUser() != null && currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            purchaseMovieOnAPremiumAccount(action, currentPage, output, movies);
        } else if (currentPage.getCurrentUser() != null && currentPage.getCurrentUser().getCredentials().getAccountType().equals("standard")) {
            purchaseMovie(action, currentPage, output, movies);
        }
    }

    public void purchaseMovieOnAPremiumAccount(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentUser().getNumFreePremiumVideos() > 0) {
            currentPage.getCurrentUser().setNumFreePremiumVideos(currentPage.getCurrentUser().getNumFreePremiumVideos() - 1);
            // poate grupate sa nu mai fie asa multe linii
            MoviesInput purchasedMovie = seeDetails(action, currentPage, output, movies);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
        } else {
            purchaseMovie(action, currentPage, output, movies);
        }
    }

    public void purchaseMovie(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 10);
        MoviesInput purchasedMovie = seeDetails(action, currentPage, output, movies);
        currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
    }

    public void watch(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        MoviesInput movie = new MoviesInput();
        MoviesInput purchasedMovie = movie.isPurchased(movies, action.getMovie());

        if ((currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
            || currentPage.getCurrentPageName().equals("Homepage autentificat")) && purchasedMovie != null) {
            action.setError(null);
            currentPage.getCurrentUser().getWatchedMovies().add(purchasedMovie);
            Write.writeFilmDetails(currentPage.getCurrentUser(), movie, action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentUser(null);
            Write.writePageError(currentPage.getCurrentUser(), action, output);
        }
    }
}
