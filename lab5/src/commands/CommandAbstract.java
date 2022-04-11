package commands;
/**
 * Abstract class for commands
 */
public abstract class CommandAbstract implements CommandInterface{
    private final String name;
    private final String description;
    /**
     * Class constructor
     *
     * @param commandName        - Command's name
     * @param commandDescription - Command's description
     */
    public CommandAbstract(String commandName, String commandDescription) {
        name = commandName;
        description = commandDescription;
    }
    /**
     * Method for print information about command
     *
     * @return String with command's name and description
     */
    @Override
    public String getDescription() {
        switch (name){
            case "help":
            case "info":
            case "show":
            case "exit":
            case "max_by_real_hero":
            case "clear":
            case "save":
                return name + " : " + description;
            case "add":
            case "add_if_min":
            case "remove_greater":
            case "remove_lower":
                return name + " {element} : " + description;
            case "update":
                return name + " id {element} : " + description;
            case "remove_by_id":
                return name + " id : " + description;
            case "execute_script":
                return name + " file_name : " + description;
            case "count_greater_than_weapon_type":
                return name + " weaponType " + description;
            case "filter_by_impact_speed":
                return name + " impactSpeed " + description;
            default:
                return name + " name : " + description;

        }
    }
    /**
     * Universal method to executing commands
     */
    @Override
    public abstract Object execute(String argName);
}
