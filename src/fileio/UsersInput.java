package fileio;

import Database.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersInput {
    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumVideos;
    private ArrayList<MoviesInput> purchasedMovies;
    private ArrayList<MoviesInput> watchedMovies;
    private ArrayList<MoviesInput> likedMovies;
    private ArrayList<MoviesInput> ratedMovies;
    private ArrayList<MoviesInput> currentMoviesList;
    private ArrayList<MoviesInput> filteredMovies;

    public UsersInput() {
    }

    public UsersInput(CredentialsInput credentials) {
        this.credentials = credentials;
        this.tokensCount = 0;
        this.numFreePremiumVideos = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.currentMoviesList = new ArrayList<>();
        this.filteredMovies = new ArrayList<>();

    }
    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumVideos() {
        return numFreePremiumVideos;
    }

    public void setNumFreePremiumVideos(int numFreePremiumVideos) {
        this.numFreePremiumVideos = numFreePremiumVideos;
    }

    public ArrayList<MoviesInput> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<MoviesInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MoviesInput> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<MoviesInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MoviesInput> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<MoviesInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MoviesInput> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<MoviesInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<MoviesInput> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(ArrayList<MoviesInput> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }
}
