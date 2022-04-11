package commands;

import util.CommandReader;
import util.Text;

import java.util.Map;
/**
 * Class for displaying all commands with explanations
 */
public class Help extends CommandAbstract {

    public Help(){
        super("help", "display help on available commands");
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) {
            System.out.println(Text.getBlueText("List of command:"));
            Map<String, CommandInterface> commands = CommandReader.getCommand_map();
            for (String i : commands.keySet()){
                System.out.println(commands.get(i).getDescription());
            }
            System.out.println("exit : terminate program (without saving to file)");
            return Text.getGreenText("The end!");
        }
            return Text.getRedText("Command arguments entered incorrectly!");
    }
}
