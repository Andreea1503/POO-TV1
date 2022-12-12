package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    public MoviesInput() {
        this.numLikes = 0;
        this.numRatings = 0;
        this.rating = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public ArrayList<MoviesInput> allowedMoviesForASpecificUser(ArrayList<MoviesInput> movies, UsersInput currentUser) {
        ArrayList<MoviesInput> allowedMovies = new ArrayList<>(movies);

        if (currentUser != null) {
            for (int i = 0; i < currentUser.getCredentials().getCountry().length(); i++) {
                allowedMovies.removeIf(movie -> movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry()));
            }
        }

        return allowedMovies;
    }

    public ArrayList<MoviesInput> filer(ArrayList<MoviesInput> movies, UsersInput currentUser, ActionsInput action) {
        ArrayList<MoviesInput> allowedMovies = allowedMoviesForASpecificUser(movies, currentUser);
        ArrayList<MoviesInput> filteredMovies = allowedMoviesForASpecificUser(movies, currentUser);

        if (action.getFilters().getContains() != null) {
            if (action.getFilters().getContains().getActors() != null) {
                allowedMovies.removeIf(movie -> !movie.getActors().contains(action.getFilters().getContains().getActors()));
                filteredMovies.removeIf(movie -> movie.getActors().contains(action.getFilters().getContains().getActors()));
            }

            if (action.getFilters().getContains().getGenres() != null) {
                allowedMovies.removeIf(movie -> !movie.getGenres().contains(action.getFilters().getContains().getGenres()));
                filteredMovies.removeIf(movie -> movie.getGenres().contains(action.getFilters().getContains().getGenres()));
            }
        }

        if (action.getFilters().getSort() != null) {
            if (action.getFilters().getSort().getRating() != null) {
                Collections.sort(filteredMovies, Comparator.comparing(MoviesInput::getRating).reversed());
                if (allowedMovies.equals(filteredMovies) && action.getFilters().getSort().getDuration() != null) {
                    Collections.sort(filteredMovies, Comparator.comparing(MoviesInput::getDuration).reversed());
                }
            }
        }
        return filteredMovies;
    }

    public MoviesInput isPurchased(ArrayList<MoviesInput> purchasedMovies, String movie) {
        for (MoviesInput purchasedMovie : purchasedMovies) {
            if (purchasedMovie.getName().equals(movie)) {
                return purchasedMovie;
            }
        }
        return null;
    }
    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "MovieInput{"
                + "name='"
                + name
                + '\''
                + ", year="
                + year
                + ", duration="
                + duration
                + ", genres="
                + genres
                + ", actors="
                + actors
                + ", countriesBanned="
                + countriesBanned
                + '}';
    }
}
