import util.CommandReader;
import util.JsonParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandReader commandReader = new CommandReader();
        JsonParser jsonParser = new JsonParser(scanner);
        System.out.println(jsonParser.jsonParse());
        commandReader.enable(scanner);
    }
}
