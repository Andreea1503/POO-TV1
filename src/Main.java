import java.util.*;

import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import fileio.*;
import Utils.*;
import Database.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        Input input = objectMapper.readValue(new File("C:\\Users\\Andreea\\Desktop\\tema2POO\\oop-asignments-master\\proiect1\\checker\\resources\\in\\basic_6.json"), Input.class);

        ArrayNode output = objectMapper.createArrayNode();
        Database database = Database.getInstance();
        database.databaseNavigation(input.getActions(), input.getUsers(), input.getMovies(), output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//        objectWriter.writeValue(new File(args[1]), output);
        objectWriter.writeValue(new File("C:\\Users\\Andreea\\Desktop\\tema2POO\\oop-asignments-master\\proiect1\\checker\\resources\\out\\out_6.json"), output);
    }
}
