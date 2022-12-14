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
    private ArrayList<Integer> movieRatings = null;
    public MoviesInput() {
        this.numLikes = 0;
        this.numRatings = 0;
        this.rating = 0.0;
        this.movieRatings = new ArrayList<>();
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

        if (action.getFilters().getContains() != null && action.getFilters().getContains().getActors() != null) {
            allowedMovies.removeIf(movie -> !movie.getActors().containsAll(action.getFilters().getContains().getActors()));
        }

        if (action.getFilters().getContains() != null && action.getFilters().getContains().getGenres() != null) {
            allowedMovies.removeIf(movie -> !movie.getGenres().containsAll(action.getFilters().getContains().getGenres()));
        }

        if (action.getFilters().getSort() != null) {
            if (action.getFilters().getSort().getDuration() != null) {
                if (action.getFilters().getSort().getDuration().equals("increasing")) {
                    Collections.sort(allowedMovies, Comparator.comparing(MoviesInput::getDuration));
                } else {
                    Collections.sort(allowedMovies, Comparator.comparing(MoviesInput::getDuration).reversed());
                }
                if (action.getFilters().getSort().getRating() != null) {
                    if (action.getFilters().getSort().getRating().equals("increasing")) {
                        for (int i = 0; i < allowedMovies.size(); i++) {
                            for (int j = i + 1; j < allowedMovies.size(); j++) {
                                if (allowedMovies.get(i).getDuration() == allowedMovies.get(j).getDuration()) {
                                    if (allowedMovies.get(i).getRating() > allowedMovies.get(j).getRating()) {
                                        Collections.swap(allowedMovies, i, j);
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < allowedMovies.size(); i++) {
                            for (int j = i + 1; j < allowedMovies.size(); j++) {
                                if (allowedMovies.get(i).getDuration() == allowedMovies.get(j).getDuration()) {
                                    if (allowedMovies.get(i).getRating() < allowedMovies.get(j).getRating()) {
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
                        Collections.sort(allowedMovies, Comparator.comparing(MoviesInput::getRating));
                    } else {
                        Collections.sort(allowedMovies, Comparator.comparing(MoviesInput::getRating).reversed());
                    }
                }
            }
        }
        return allowedMovies;
    }

    public MoviesInput isInList(ArrayList<MoviesInput> purchasedMovies, String movie) {
        for (MoviesInput purchasedMovie : purchasedMovies) {
            if (purchasedMovie != null && purchasedMovie.getName().equals(movie)) {
                return purchasedMovie;
            }
        }
        return null;
    }

    public Double rating(MoviesInput movie) {
        if (movie.getNumRatings() > 0) {
            Integer rating = 0;
            for (Integer userRating : movie.getMovieRatings()) {
                rating += userRating;
            }

            return (double) rating / movie.getNumRatings();
        }
        return 0.0;
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

    public ArrayList<Integer> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(ArrayList<Integer> movieRatings) {
        this.movieRatings = movieRatings;
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
