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

    @Override
    public abstract Object execute(String argName);
}
