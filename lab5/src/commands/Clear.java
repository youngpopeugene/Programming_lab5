package commands;

import util.CollectionManager;
import util.Text;
/**
 * Class for remove all elements from collection
 */
public class Clear extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear", "clear collection");
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
