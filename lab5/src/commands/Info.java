package commands;

import util.CollectionManager;
import util.Text;

public class Info extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Info (CollectionManager collectionManager){
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) return Text.getBlueText("Information about collection:\n") + collectionManager.getInfo();
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
