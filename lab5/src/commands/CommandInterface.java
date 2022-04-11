package commands;
/**
 * Interface for commands
 */
public interface CommandInterface {

    String getDescription();

    Object execute(String argName);

}
