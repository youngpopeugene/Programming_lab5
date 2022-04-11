package util;
/**
 * Class for objects with fields - name of command and name of command's argument,
 * for convenience of working with command and argument
 */
public class CommandInit {
    private final String commandName;
    private final String argName;

    public CommandInit(String commandName, String argName) {
        this.commandName = commandName;
        this.argName = argName;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getArgName() {
        return argName;
    }

    @Override
    public String toString() {
        return "CommandInit{" +
                "commandName='" + commandName + '\'' +
                ", argName='" + argName + '\'' +
                '}';
    }
}
