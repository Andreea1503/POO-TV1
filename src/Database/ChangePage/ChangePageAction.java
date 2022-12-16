package Database.ChangePage;

import Database.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.MoviesInput;

import java.util.ArrayList;

public interface ChangePageAction {
    void execute(ActionsInput action, Page currentPage, ArrayList<MoviesInput> movies, ArrayNode output);
}
