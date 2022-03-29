package util;

import commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {
    private static Map<String, CommandInterface> command_map = new HashMap<>();
    private static CollectionManager collectionManager = new CollectionManager();
    private static CreatorHumanBeing creatorHumanBeing = new CreatorHumanBeing();

    static{
        command_map.put("help", new Help());
        command_map.put("info", new Info(collectionManager));
        command_map.put("show", new Show(collectionManager) );
        command_map.put("add", new Add(collectionManager, creatorHumanBeing));
        command_map.put("update", new Update(collectionManager, creatorHumanBeing));
        command_map.put("remove_by_id", new Remove_By_Id(collectionManager));
        command_map.put("clear", new Clear(collectionManager));
        command_map.put("save", new Save());
        command_map.put("execute_script", new Execute_Script());
        command_map.put("add_if_min", new Add_If_Min(collectionManager, creatorHumanBeing));
        command_map.put("remove_greater", new Remove_Greater(collectionManager, creatorHumanBeing));
        command_map.put("remove_lower", new Remove_Lower(collectionManager, creatorHumanBeing));
        command_map.put("max_by_real_hero", new Max_By_Real_Hero(collectionManager));
        command_map.put("count_greater_than_weapon_type", new Count_Greater_Than_Weapon_Type(collectionManager));
        command_map.put("filter_by_impact_speed", new Filter_By_Impact_Speed(collectionManager));
    }

    public static CommandInit readCommand(String inputString){
        String command;
        String arg;
        String line = inputString;
        Pattern commandName = Pattern.compile("^\\w+\\s+");
        Pattern argName = Pattern.compile("^.+");

        Matcher matcher = commandName.matcher(line);
        if (matcher.find()) command = matcher.group();
        else return null;

        line = line.substring(command.length());
        matcher = argName.matcher(line);
        arg = matcher.find() ? matcher.group() : "";

        return new CommandInit(command.trim(), arg.trim());
    }

    public void enable(Scanner input){
        String nextLine = "";
        while (!nextLine.equals("exit")) {
            System.out.println(Text.getBlueText("Enter the command:"));
            try {
                nextLine = input.nextLine() + " ";
            }catch(NoSuchElementException e){
                System.exit(0);
            }
            CommandInit newCommand = readCommand(nextLine);
            if (newCommand == null){
                try {
                    throw new IOException("Input or output error!");
                } catch(IOException err) {
                    System.err.println(err.getMessage());
                }
            } else if (newCommand.getCommandName().equals("exit") && newCommand.getArgName().equals("")) {
                System.out.println(Text.getGreenText("Thank you for working in this program!"));
                System.exit(0);
            } else execute(newCommand);
        }
    }

    public static void execute(CommandInit newCommand){
        String command = newCommand.getCommandName();
        String arg = newCommand.getArgName();
        if(command_map.containsKey(command)){
            System.out.println(command_map.get(command).execute(arg));
        } else {
            System.out.println(Text.getRedText("Command not found. Please, try again!"));
        }
    }

    public static Map<String, CommandInterface> getCommand_map() {
        return command_map;
    }
}
