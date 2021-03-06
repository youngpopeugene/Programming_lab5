package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.Text;

/**
 * Class for remove element by id
 */
public class Remove_By_Id extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Remove_By_Id(CollectionManager collectionManager){
        super("remove_by_id", "remove element from collection by its id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        if(isPositiveLong(argName)){
            HumanBeing human = collectionManager.getById(Long.parseLong(argName));
            if (human != null) {
                collectionManager.remove(human);
                CollectionManager.getUsedId().remove(human.getId());
            }
            else return Text.getRedText("An object with this ID does not exist!");
            return Text.getGreenText("Object has been removed!");
        }
        return Text.getRedText("ID should be not null positive long!");
    }

    public boolean isPositiveLong(String line){
        try {
            return Long.parseLong(line) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
