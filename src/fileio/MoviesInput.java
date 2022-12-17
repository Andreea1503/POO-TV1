package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class that contains the input data for the movies
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesInput {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private int numRatings;
    private Double rating;
    private ArrayList<Integer> movieRatings;
    public MoviesInput() {
        this.numLikes = 0;
        this.numRatings = 0;
        this.rating = 0.0;
        this.movieRatings = new ArrayList<>();
    }

    /**
     * Method that returns the name of the movie
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that sets the name of the movie
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Method that returns the year of the movie
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Method that sets the year of the movie
     * @param year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Method that returns the duration of the movie
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method that sets the duration of the movie
     * @param duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Method that returns the genres of the movie
     * @return genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Method that sets the genres of the movie
     * @param genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Method that returns the actors of the movie
     * @return actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     * Method that sets the actors of the movie
     * @param actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     * Method that returns the countries banned of the movie
     * @return countriesBanned
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Method that sets the countries banned of the movie
     * @param countriesBanned
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * Method that return the allowed movies for the current user depending on the country he lives
     * in.
     * @param movies
     * @param currentUser
     * @return allowedMovies
     */
    public ArrayList<MoviesInput> allowedMoviesForUser(final ArrayList<MoviesInput> movies,
                                                       final UsersInput currentUser) {
        ArrayList<MoviesInput> allowedMovies = new ArrayList<>(movies);

        if (currentUser != null) {
            for (int i = 0; i < currentUser.getCredentials().getCountry().length(); i++) {
                allowedMovies.removeIf(movie -> movie.getCountriesBanned().
                        contains(currentUser.getCredentials().getCountry()));
            }
        }

        return allowedMovies;
    }

    /**
     * Method that filters the movies by the given filters
     * @param movies
     * @param currentUser
     * @param action
     * @return
     */
    public ArrayList<MoviesInput> filter(final ArrayList<MoviesInput> movies,
                                         final UsersInput currentUser, final ActionsInput action) {
        ArrayList<MoviesInput> allowedMovies = new ArrayList<>(allowedMoviesForUser(
                movies, currentUser));

        if (action.getFilters().getContains() != null && action.getFilters().getContains().
                getActors() != null) {
            filterByActors(allowedMovies, action);
        }

        if (action.getFilters().getContains() != null && action.getFilters().getContains().
                getGenre() != null) {
            filterByGenre(allowedMovies, action);
        }

        if (action.getFilters().getSort() != null) {
            filterByDurationAndRating(allowedMovies, action);
        }
        return allowedMovies;
    }

    /**
     * Method that filters the movies by the given actors
     * If the actor is not in the movie, the movie is removed from the list
     * @param movies
     * @param action
     * @return movies
     */
    private ArrayList<MoviesInput> filterByActors(final ArrayList<MoviesInput> movies,
                                                  final ActionsInput action) {
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < action.getFilters().getContains().getActors().size(); j++) {
                if (!movies.get(i).getActors().contains(action.getFilters().getContains().
                        getActors().get(j))) {
                    movies.remove(i);
                    i--;
                }
            }
        }
        return movies;
    }

    /**
     * Method that filters the movies by the given genre
     * If the genre is not in the movie, the movie is removed from the list
     * @param movies
     * @param action
     * @return movies
     */
    private ArrayList<MoviesInput> filterByGenre(final ArrayList<MoviesInput> movies,
                                                 final ActionsInput action) {
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < action.getFilters().getContains().getGenre().size(); j++) {
                if (!movies.get(i).getGenres().contains(action.getFilters().getContains().
                        getGenre().get(j))) {
                    movies.remove(i);
                    i--;
                }
            }
        }
        return movies;
    }

    /**
     * Method that filters the movies by the given duration and rating
     * The movies are first sorted by duration and then by rating if both parameters are given
     * @param allowedMovies
     * @param action
     * @return
     */
    private ArrayList<MoviesInput> filterByDurationAndRating(
            final ArrayList<MoviesInput> allowedMovies, final ActionsInput action) {
        if (action.getFilters().getSort().getDuration() != null) {
            if (action.getFilters().getSort().getDuration().equals("increasing")) {
                Collections.sort(allowedMovies, Comparator.
                        comparing(MoviesInput::getDuration));
            } else {
                Collections.sort(allowedMovies, Comparator.
                        comparing(MoviesInput::getDuration).reversed());
            }
            if (action.getFilters().getSort().getRating() != null) {
                if (action.getFilters().getSort().getRating().equals("increasing")) {
                    for (int i = 0; i < allowedMovies.size(); i++) {
                        for (int j = i + 1; j < allowedMovies.size(); j++) {
                            if (allowedMovies.get(i).getDuration()
                                    == allowedMovies.get(j).getDuration()) {
                                if (allowedMovies.get(i).getRating()
                                        > allowedMovies.get(j).getRating()) {
                                    Collections.swap(allowedMovies, i, j);
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < allowedMovies.size(); i++) {
                        for (int j = i + 1; j < allowedMovies.size(); j++) {
                            if (allowedMovies.get(i).getDuration()
                                    == allowedMovies.get(j).getDuration()) {
                                if (allowedMovies.get(i).getRating()
                                        < allowedMovies.get(j).getRating()) {
                                    Collections.swap(allowedMovies, i, j);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (action.getFilters().getSort().getRating() != null) {
                if (action.getFilters().getSort().getRating().equals("increasing")) {
                    Collections.sort(allowedMovies, Comparator.
                            comparing(MoviesInput::getRating));
                } else {
                    Collections.sort(allowedMovies, Comparator.
                            comparing(MoviesInput::getRating).reversed());
                }
            }
        }
        return allowedMovies;
    }

    /**
     * Method that returns a movie if it is in the list of movies
     * @param movies
     * @param movieName
     * @return movie
     */
    public MoviesInput isInList(final ArrayList<MoviesInput> movies, final String movieName) {
        for (MoviesInput movie : movies) {
            if (movie != null && movie.getName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Method that calculates the rating of a movie
     * @param movie
     * @return rating
     */
    public Double rating(final MoviesInput movie) {
        if (movie.getNumRatings() > 0) {
            Integer rating = 0;
            for (Integer userRating : movie.getMovieRatings()) {
                rating += userRating;
            }

            return (double) rating / movie.getNumRatings();
        }
        return 0.0;
    }

    /**
     * Method that searched for a movie in the list of movies by the word the name of the movie
     * starts with
     * @param action
     * @param currentUser
     * @return searchedMovies
     */
    public static ArrayList<MoviesInput> searchMovie(final ActionsInput action,
                                                     final UsersInput currentUser) {
        ArrayList<MoviesInput> searchedMovies = new ArrayList<>(currentUser.getCurrentMoviesList());

        for (int i = 0; i < searchedMovies.size(); i++) {
            String title = action.getStartsWith();
            if (!searchedMovies.get(i).getName().startsWith(title)) {
                searchedMovies.remove(i);
                i--;
            }
        }
        return searchedMovies;
    }

    /**
     * Method that returns the movie we want to see the details of
     * @param action
     * @param movies
     * @return movie
     */
    public static MoviesInput seeDetails(final ActionsInput action,
                                         final ArrayList<MoviesInput> movies) {
        for (MoviesInput movie : movies) {
            if (movie != null) {
                if (action.getMovie().equals(movie.getName())) {
                    return movie;
                }
            }
        }
        return null;
    }

    /**
     * Method that return the number of likes of the movie
     * @return numLikes
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * Method that sets the number of likes of the movie
     * @param numLikes
     */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Method that returns the number of views of the movie
     * @return numViews
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     * Method that sets the number of views of the movie
     * @param numRatings
     */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * Method that returns the list of ratings of the movie
     * @return movieRatings
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Method that sets the list of ratings of the movie
     * @param rating
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    /**
     * Method that returns the list of ratings of the movie
     * @return movieRatings
     */
    public ArrayList<Integer> getMovieRatings() {
        return movieRatings;
    }

    /**
     * Method that sets the list of ratings of the movie
     * @param movieRatings
     */
    public void setMovieRatings(final ArrayList<Integer> movieRatings) {
        this.movieRatings = movieRatings;
    }
}
