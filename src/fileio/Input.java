package fileio;

import java.util.ArrayList;

/**
 * This class is used to read the input file and store the data in the
 * appropriate classes.
 */
public class Input {
    private ArrayList<UsersInput> users;
    private ArrayList<MoviesInput> movies;
    private ArrayList<ActionsInput> actions;

    public Input() {
    }

    /**
     * Method that returns the list of users.
     * @return users
     */
    public ArrayList<UsersInput> getUsers() {
        return users;
    }

    /**
     * Method that sets the list of users.
     * @param users
     */
    public void setUsers(final ArrayList<UsersInput> users) {
        this.users = users;
    }

    /**
     * Method that returns the list of movies.
     * @return movies
     */
    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    /**
     * Method that sets the list of movies.
     * @param movies
     */
    public void setMovies(final ArrayList<MoviesInput> movies) {
        this.movies = movies;
    }

    /**
     * Method that returns the list of actions.
     * @return actions
     */
    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    /**
     * Method that sets the list of actions.
     * @param actions
     */
    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }
}
