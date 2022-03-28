package commands;

import util.CollectionManager;
import util.Text;

public class Clear extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }
    @Override
    public Object execute(String argName) {
        if (argName.equals("")) {
            collectionManager.clear();
            return Text.getGreenText("Cleared successfully!");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
