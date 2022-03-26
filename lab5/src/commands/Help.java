package commands;

import util.CommandReader;
import util.Text;

import java.util.Map;

public class Help extends CommandAbstract {

    public Help(){
        super("help", "вывести справку по доступным командам");
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) {
            System.out.println(Text.getBlueText("List of command:"));
            Map<String, CommandInterface> commands = CommandReader.getCommand_map();
            for (String i : commands.keySet()){
                System.out.println(commands.get(i).getDescription());
            }
            System.out.println("exit : завершить программу (без сохранения в файл)");
            return Text.getGreenText("The end!");
        }
            return Text.getRedText("Command arguments entered incorrectly!");
    }
}
