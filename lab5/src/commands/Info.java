package commands;

import util.CollectionManager;
import util.Text;
/**
 * Class for displaying all commands with explanations
 */
public class Info extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Info (CollectionManager collectionManager){
        super("info", "print information about the collection to standard output");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) return Text.getBlueText("Information about collection:\n") + collectionManager.getInfo();
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
