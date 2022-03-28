package commands;

import util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Execute_Script extends CommandAbstract {
    private static List<String> scriptList = new ArrayList<>();

    public Execute_Script() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) return Text.getRedText("You should write a script path !!!");
        else{
            try{
                InputStream is = new FileInputStream(argName);

                if (scriptList.contains(argName)) return Text.getRedText("Recursion is not possible!");
                else scriptList.add(argName);

                Reader isr = new InputStreamReader(is);
                System.out.println(Text.getBlueText("Execute_script in progress!"));

                int data = 0;
                List<Character> line = new ArrayList<>();

                while(data != -1){
                    data = isr.read();
                    char chr = (char) data;

                    if (data == 10 || data == -1){
                        String stringLine = line.stream().map(e->e.toString()).collect(Collectors.joining());
                        if(line.indexOf('\n') == 0) stringLine = stringLine.substring(1);
                        stringLine += " ";
                        line.clear();

                        CommandInit newCommand = CommandReader.readCommand(stringLine);
                        if (newCommand == null){
                            try {
                                throw new IOException("Input or output error!");
                            } catch(IOException err) {
                                System.err.println(err.getMessage());
                            }
                        } else if (newCommand.getCommandName().equals("exit") && newCommand.getArgName().equals("")) {
                            System.out.println(Text.getGreenText("Thank you for working in this program!"));
                            System.exit(0);
                        } else {
                            if (newCommand.getCommandName().equals("add")
                                    || newCommand.getCommandName().equals("add_if_min")
                                    || newCommand.getCommandName().equals("update")
                                    || newCommand.getCommandName().equals("remove_greater")
                                    || newCommand.getCommandName().equals("remove_lower")) {

                                List<Character> fields = new ArrayList<>();
                                int countFields = 0;
                                do {
                                    data = isr.read();
                                    if (data == -1) break;
                                    chr = (char) data;
                                    fields.add(chr);
                                    if (chr == 10) countFields += 1;
                                }while(countFields != 10);
                                String stringFields = fields.stream().map(e -> e.toString()).collect(Collectors.joining());
                                CreatorHumanBeing.setInput(new Scanner(stringFields));

                            }
                            CommandReader.execute(newCommand);
                        }
                    }
                    line.add(chr);
                }
                isr.close();
                CreatorHumanBeing.setInput(new Scanner(System.in));

            } catch (FileNotFoundException ex){
                return Text.getRedText("File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scriptList.remove(argName);
        return Text.getGreenText("That's all with command execute_script!");
    }
}
