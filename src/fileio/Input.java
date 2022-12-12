package fileio;

import java.util.ArrayList;
public class Input {
    private ArrayList<UsersInput> users;
    private ArrayList<MoviesInput> movies;
    private ArrayList<ActionsInput> actions;

    public Input() {
    }

    public ArrayList<UsersInput> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UsersInput> users) {
        this.users = users;
    }

    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MoviesInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input{"
                + "users="
                + users
                + ", movies="
                + movies
                + ", action="
                + actions
                + '}';
    }

}
