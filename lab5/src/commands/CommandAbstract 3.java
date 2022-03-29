package commands;

public abstract class CommandAbstract implements CommandInterface{
    private final String name;
    private final String description;

    public CommandAbstract(String commandName, String commandDescription) {
        name = commandName;
        description = commandDescription;
    }

    @Override
    public String getDescription() {
        return switch (name) {
            case "help", "info", "show", "exit", "max_by_real_hero", "clear", "save" -> name + " : " + description;
            case "add", "add_if_min", "remove_greater", "remove_lower" -> name + " {element} : " + description;
            case "update" -> name + " id {element} : " + description;
            case "remove_by_id" -> name + " id : " + description;
            case "execute_script" -> name + " file_name : " + description;
            case "count_greater_than_weapon_type" -> name + " weaponType " + description;
            case "filter_by_impact_speed" -> name + " impactSpeed " + description;
            default -> name + " name : " + description;
        };
    }

    @Override
    public abstract Object execute(String argName);
}
