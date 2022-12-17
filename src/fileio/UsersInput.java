package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Class that contains the users input
 */
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

    public UsersInput(final CredentialsInput credentials) {
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

    /**
     * Method that returns the credentials of the user
     * @return credentials
     */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /**
     * Method that sets the credentials of the user
     * @param credentials
     */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    /**
     * Method that returns the number of tokens of the user
     * @return tokensCount
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Method that sets the number of tokens of the user
     * @param tokensCount
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Method that returns the number of free premium videos of the user
     * @return numFreePremiumVideos
     */
    public int getNumFreePremiumVideos() {
        return numFreePremiumVideos;
    }

    /**
     * Method that sets the number of free premium videos of the user
     * @param numFreePremiumVideos
     */
    public void setNumFreePremiumVideos(final int numFreePremiumVideos) {
        this.numFreePremiumVideos = numFreePremiumVideos;
    }

    /**
     * Method that returns the list of purchased movies of the user
     * @return purchasedMovies
     */
    public ArrayList<MoviesInput> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * Method that sets the list of purchased movies of the user
     * @param purchasedMovies
     */
    public void setPurchasedMovies(final ArrayList<MoviesInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * Method that returns the list of watched movies of the user
     * @return watchedMovies
     */
    public ArrayList<MoviesInput> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Method that sets the list of watched movies of the user
     * @param watchedMovies
     */
    public void setWatchedMovies(final ArrayList<MoviesInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Method that returns the list of liked movies of the user
     * @return likedMovies
     */
    public ArrayList<MoviesInput> getLikedMovies() {
        return likedMovies;
    }

    /**
     * Method that sets the list of liked movies of the user
     * @param likedMovies
     */
    public void setLikedMovies(final ArrayList<MoviesInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Method that returns the list of rated movies of the user
     * @return ratedMovies
     */
    public ArrayList<MoviesInput> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Method that sets the list of rated movies of the user
     * @param ratedMovies
     */
    public void setRatedMovies(final ArrayList<MoviesInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /**
     * Method that returns the list of current movies of the user
     * @return currentMoviesList
     */
    public ArrayList<MoviesInput> getCurrentMoviesList() {
        return currentMoviesList;
    }

    /**
     * Method that sets the list of current movies of the user
     * @param currentMoviesList
     */
    public void setCurrentMoviesList(final ArrayList<MoviesInput> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }
}
