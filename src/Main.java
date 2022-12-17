import fileio.Input;
import database.Database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * This is the entry point of the application, where the input is read from the input file and
     * the output is written in the output file. The database singleton is instantiated here.
     * @param args [0] = input file path
     *             [1] = output file path
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();
        Database database = Database.getInstance();
        database.databaseNavigation(input.getActions(), input.getUsers(), input.getMovies(),
                output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
