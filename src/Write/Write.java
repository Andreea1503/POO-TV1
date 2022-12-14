package Write;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.MoviesInput;
import fileio.UsersInput;

import java.util.ArrayList;
import java.util.Objects;

public class Write {
    public Write() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writePageError(UsersInput currentUser, ActionsInput action, ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        if (currentUser != null) {
            ArrayNode movies = movieList(currentUser.getCurrentMoviesList());
            loginError.set("currentMoviesList", movies);
        } else {
            ArrayNode movies = movieList(new ArrayList<>());
            loginError.set("currentMoviesList", movies);
        }

        ObjectNode user = objectMapper.createObjectNode();
        if (currentUser != null) {
            user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }
        output.add(loginError);
    }

    public static ArrayNode movieList(ArrayList<MoviesInput> currentMoviesList) {
        ArrayNode movies = objectMapper.createArrayNode();

        if (currentMoviesList != null) {
            for (int i = 0; i < currentMoviesList.size(); i++) {
                ObjectNode movie = objectMapper.createObjectNode();

//                if (currentMoviesList.get(i) != null) {
                    movie.put("name", currentMoviesList.get(i).getName());
                    movie.put("year", currentMoviesList.get(i).getYear());
                    movie.put("duration", currentMoviesList.get(i).getDuration());

                    ArrayNode genres = objectMapper.createArrayNode();
                    for (int j = 0; j < currentMoviesList.get(i).getGenres().size(); j++) {
                        genres.add(currentMoviesList.get(i).getGenres().get(j));
                    }
                    movie.set("genres", genres);

                    ArrayNode actors = objectMapper.createArrayNode();
                    for (int j = 0; j < currentMoviesList.get(i).getActors().size(); j++) {
                        actors.add(currentMoviesList.get(i).getActors().get(j));
                    }
                    movie.set("actors", actors);

                    ArrayNode countriesBanned = objectMapper.createArrayNode();
                    for (int j = 0; j < currentMoviesList.get(i).getCountriesBanned().size(); j++) {
                        countriesBanned.add(currentMoviesList.get(i).getCountriesBanned().get(j));
                    }
                    movie.set("countriesBanned", countriesBanned);
                    movie.put("numLikes", currentMoviesList.get(i).getNumLikes());
                    movie.put("numRatings", currentMoviesList.get(i).getNumRatings());
                    movie.put("rating", currentMoviesList.get(i).getRating());

                    movies.add(movie);
//                }
            }
        }

        return movies;
    }

    public static ObjectNode user(UsersInput currentUser) {
        ObjectNode user = objectMapper.createObjectNode();

        ObjectNode credentials = objectMapper.createObjectNode();

        credentials.put("name", currentUser.getCredentials().getName());
        credentials.put("password", currentUser.getCredentials().getPassword());
        credentials.put("accountType", currentUser.getCredentials().getAccountType());
        credentials.put("country", currentUser.getCredentials().getCountry());
        credentials.put("balance", currentUser.getCredentials().getBalance());

        user.set("credentials", credentials);

        user.put("tokensCount", currentUser.getTokensCount());
        user.put("numFreePremiumMovies", currentUser.getNumFreePremiumVideos());
        ArrayNode purchasedMovies = objectMapper.createArrayNode();
        purchasedMovies.addAll(movieList(currentUser.getPurchasedMovies()));
        user.set("purchasedMovies", purchasedMovies);

        ArrayNode watchedMovies = objectMapper.createArrayNode();
        watchedMovies.addAll(movieList(currentUser.getWatchedMovies()));
        user.set("watchedMovies", watchedMovies);

        ArrayNode likedMovies = objectMapper.createArrayNode();
        likedMovies.addAll(movieList(currentUser.getLikedMovies()));
        user.set("likedMovies", likedMovies);

        ArrayNode ratedMovies = objectMapper.createArrayNode();
        ratedMovies.addAll(movieList(currentUser.getRatedMovies()));
        user.set("ratedMovies", ratedMovies);

        return user;
    }

    public static void writeMoviesError(ArrayList<MoviesInput> movies, ActionsInput action, UsersInput currentUser, ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        MoviesInput movie = new MoviesInput();
        ArrayList<MoviesInput> allowedMovies = movie.allowedMoviesForASpecificUser(movies, currentUser);

        if (currentUser != null) {
            ArrayNode movieList = movieList(allowedMovies);
            loginError.set("currentMoviesList", movieList);
        } else {
            ArrayNode movieList = movieList(new ArrayList<>());
            loginError.set("currentMoviesList", movieList);
        }

        ObjectNode user = objectMapper.createObjectNode();
        if (currentUser != null) {
            user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }

        output.add(loginError);
    }

    public static void writeSearchError(ArrayList<MoviesInput> movies, ActionsInput action, UsersInput currentUser, ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        ArrayList<MoviesInput> searchMovies = new ArrayList<>(currentUser.getCurrentMoviesList());

        for (int i = 0; i < searchMovies.size(); i++) {
            String title = action.getStartsWith();
            if (!searchMovies.get(i).getName().startsWith(title)) {
                searchMovies.remove(i);
                i--;
            }
        }

        ArrayNode moviesOutput = objectMapper.createArrayNode();
        moviesOutput = movieList(searchMovies);

        loginError.set("currentMoviesList", moviesOutput);

        ObjectNode user = objectMapper.createObjectNode();
        if (currentUser != null) {
            user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }

        output.add(loginError);
    }

    public static void writeFilter(ArrayList<MoviesInput> movies, UsersInput currentUser, ActionsInput action, ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        ArrayNode moviesOutput = movieList(movies);
        loginError.set("currentMoviesList", moviesOutput);

        ObjectNode user = objectMapper.createObjectNode();
        if (currentUser != null) {
            user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }

        output.add(loginError);
    }

    public static void writeFilmDetails(UsersInput currentUser, MoviesInput movie, ActionsInput action, ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        ArrayNode moviesOutput = objectMapper.createArrayNode();
        ObjectNode movieDetails = objectMapper.createObjectNode();
        if (movie != null) {
            movieDetails.put("name", movie.getName());
            movieDetails.put("year", movie.getYear());
            movieDetails.put("duration", movie.getDuration());

            ArrayNode genres = objectMapper.createArrayNode();
            for (int j = 0; j < movie.getGenres().size(); j++) {
                genres.add(movie.getGenres().get(j));
            }
            movieDetails.set("genres", genres);

            ArrayNode actors = objectMapper.createArrayNode();
            for (int j = 0; j < movie.getActors().size(); j++) {
                actors.add(movie.getActors().get(j));
            }
            movieDetails.set("actors", actors);

            ArrayNode countriesBanned = objectMapper.createArrayNode();
            for (int j = 0; j < movie.getCountriesBanned().size(); j++) {
                countriesBanned.add(movie.getCountriesBanned().get(j));
            }
            movieDetails.set("countriesBanned", countriesBanned);

            movieDetails.put("numLikes", movie.getNumLikes());
            movieDetails.put("numRatings", movie.getNumRatings());
            MoviesInput moviesRating = new MoviesInput();
            movie.setRating(moviesRating.rating(movie));
            movieDetails.put("rating", movie.getRating());
        }

        moviesOutput.add(movieDetails);
        loginError.set("currentMoviesList", moviesOutput);

        ObjectNode user = objectMapper.createObjectNode();
        if (currentUser != null) {
            user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }

        output.add(loginError);
    }
}
