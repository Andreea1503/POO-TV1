package Database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Collections;

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
                purchase(action, currentPage, output);
            }
            case "watch" -> {
                watch(action, currentPage, output);
            }
            case "like" -> {
                like(action, currentPage, output);
            }
            case "rate" -> {
                rate(action, currentPage, output);
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
                    Write.writePageError(null, action, output);
                }
            }
            case "register" -> {
                if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    currentPage.setCurrentPageName("register");
                } else {
                    action.setError("Error");
                    Write.writeMoviesError(movies, action, null, output);
                }
            }
            case "movies" -> {
                if ((currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details")
                    || currentPage.getCurrentPageName().equals("upgrades")) && currentPage.getCurrentUser() != null) {
                    currentPage.setCurrentPageName("movies");
                    action.setError(null);
                    MoviesInput movie = new MoviesInput();
                    currentPage.getCurrentUser().setCurrentMoviesList(movie.allowedMoviesForASpecificUser(movies, currentPage.getCurrentUser()));
                    Write.writeMoviesError(movies, action, currentPage.getCurrentUser(), output);
                } else {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                }
            }
            case "see details" -> {
                if (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("see details")) {
                    currentPage.setCurrentPageName("see details");
                    MoviesInput movie = new MoviesInput();
                    ArrayList<MoviesInput> allowedMovies = movie.allowedMoviesForASpecificUser(movies, currentPage.getCurrentUser());
                    MoviesInput allowedMovie = seeDetails(action, currentPage, output, allowedMovies);
                    currentPage.getCurrentUser().setCurrentMoviesList(new ArrayList<>());
                    currentPage.getCurrentUser().getCurrentMoviesList().add(allowedMovie);

                    if (allowedMovie != null) {
                        Write.writeFilmDetails(currentPage.getCurrentUser(), allowedMovie, action, output);
                    } else {
                        action.setError("Error");
                        Write.writePageError(null, action, output);
                    }
                } else {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                }
            }
            case "upgrades" -> {
                if (currentPage.getCurrentPageName().equals("see details") || currentPage.getCurrentPageName().equals("Homepage autentificat")) {
                    currentPage.setCurrentPageName("upgrades");
                } else {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                }
            }
            case "logout" -> {
                if (currentPage.getCurrentPageName().equals("login") || currentPage.getCurrentPageName().equals("register")
                    || currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                } else {
                    currentPage.setCurrentPageName("Homepage neautentificat");
                    currentPage.setCurrentUser(null);
                }
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
                currentPage.setCurrentPageName("Homepage neautentificat");
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    public void registerIntoTheDatabase(ArrayList<UsersInput> users, ActionsInput action, Page currentPage, ArrayNode output) {
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
            action.setError(null);
            Write.writePageError(currentPage.getCurrentUser(),  action, output);
        } else {
            action.setError("Error");
            currentPage.setCurrentPageName("Homepage neautentificat");
            Write.writePageError(null, action, output);
        }
    }

    public void searchIntoTheDatabase(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentPageName().equals("movies")) {
            action.setError(null);
            Write.writeSearchError(movies, action, currentPage.getCurrentUser(), output);
        } else {
            action.setError("Error");
            Write.writeSearchError(movies,  action, null, output);
        }
    }

    public void filterIntoTheDatabase(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentPageName().equals("movies")) {
            action.setError(null);
            MoviesInput movie = new MoviesInput();
            ArrayList<MoviesInput> filteredMovies = movie.filer(movies, currentPage.getCurrentUser(), action);
            if (filteredMovies != null) {
                currentPage.getCurrentUser().setCurrentMoviesList(filteredMovies);
            }
            Write.writeFilter(filteredMovies, currentPage.getCurrentUser(), action, output);
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    public MoviesInput seeDetails(ActionsInput action, Page currentPage, ArrayNode output, ArrayList<MoviesInput> movies) {
        for (MoviesInput movie : movies) {
            if (movie != null) {
                if (action.getMovie().equals(movie.getName())) {
                    return movie;
                }
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
            Write.writePageError(null, action, output);
        }
    }

    public void buyPremiumAccount(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentPageName().equals("upgrades") && currentPage.getCurrentUser().getTokensCount() >= 10) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 10);
            currentPage.getCurrentUser().getCredentials().setAccountType("premium");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    public void purchase(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentUser().getCurrentMoviesList().get(0) != null && currentPage.getCurrentPageName().equals("see details")) {
            MoviesInput purchasedMovies = purchaseMovieOnAccount(action, currentPage, output);
            if (purchasedMovies != null) {
                Write.writeFilmDetails(currentPage.getCurrentUser(), purchasedMovies, action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    public MoviesInput purchaseMovieOnAccount(ActionsInput action, Page currentPage, ArrayNode output) {
        MoviesInput purchasedMovie = currentPage.getCurrentUser().getCurrentMoviesList().get(0);
        if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium") && currentPage.getCurrentUser().getNumFreePremiumVideos() > 0) {
            currentPage.getCurrentUser().setNumFreePremiumVideos(currentPage.getCurrentUser().getNumFreePremiumVideos() - 1);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
        } else if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("standard") || currentPage.getCurrentUser().getNumFreePremiumVideos() == 0) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 2);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
        }
        return purchasedMovie;
    }

    public void watch(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput purchasedMovie = currentPage.getCurrentUser().getCurrentMoviesList().get(0);

            if (purchasedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            MoviesInput movie = new MoviesInput();
            MoviesInput watchedMovie = movie.isInList(currentPage.getCurrentUser().getPurchasedMovies(), purchasedMovie.getName());

            if (watchedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details"))) {
                action.setError(null);
                currentPage.getCurrentUser().getWatchedMovies().add(watchedMovie);
                Write.writeFilmDetails(currentPage.getCurrentUser(), watchedMovie, action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }

    public void like(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput watchedMovie = currentPage.getCurrentUser().getCurrentMoviesList().get(0);

            if (watchedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            MoviesInput movie = new MoviesInput();
            MoviesInput likedMovie = movie.isInList(currentPage.getCurrentUser().getWatchedMovies(), watchedMovie.getName());


            if (likedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details"))) {
                action.setError(null);
                currentPage.getCurrentUser().getLikedMovies().add(likedMovie);
                currentPage.getCurrentUser().getLikedMovies().get(currentPage.getCurrentUser().getLikedMovies().size() - 1).setNumLikes(currentPage.getCurrentUser().getLikedMovies().get(currentPage.getCurrentUser().getLikedMovies().size() - 1).getNumLikes() + 1);
                Write.writeFilmDetails(currentPage.getCurrentUser(), currentPage.getCurrentUser().getLikedMovies().get(currentPage.getCurrentUser().getLikedMovies().size() - 1), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }

    public void rate(ActionsInput action, Page currentPage, ArrayNode output) {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput watchedMovie = currentPage.getCurrentUser().getCurrentMoviesList().get(0);

            if (watchedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }
            MoviesInput movie = new MoviesInput();
            MoviesInput ratedMovie = movie.isInList(currentPage.getCurrentUser().getWatchedMovies(), watchedMovie.getName());

            if (ratedMovie != null && (currentPage.getCurrentPageName().equals("movies") || currentPage.getCurrentPageName().equals("upgrades")
                    || currentPage.getCurrentPageName().equals("Homepage autentificat") || currentPage.getCurrentPageName().equals("see details")) && action.getRate() <= 5) {
                action.setError(null);
                currentPage.getCurrentUser().getRatedMovies().add(ratedMovie);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).setNumRatings(currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).getNumRatings() + 1);
                currentPage.getCurrentUser().getRatedMovies().get(currentPage.getCurrentUser().getRatedMovies().size() - 1).getMovieRatings().add(action.getRate());
                Write.writeFilmDetails(currentPage.getCurrentUser(), ratedMovie, action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        }
    }
}
