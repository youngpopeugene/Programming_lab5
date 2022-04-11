package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;
/**
 * Class to update element in collection by id
 */
public class Update extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;

    public Update(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("update",
                "update the value of the collection element whose id is equal to the given one");
        this.collectionManager = collectionManager;
        this.creatorHumanBeing = creatorHumanBeing;
    }

    @Override
    public Object execute(String argName) {
        if(isPositiveLong(argName)){
            HumanBeing human = collectionManager.getById(Long.parseLong(argName));
            if(human == null) return Text.getRedText("An object with this ID does not exist!");
            HumanBeing updatingHuman = creatorHumanBeing.createHumanBeing();
            if (updatingHuman == null) return Text.getRedText("Object hasn't been updated! (fields entered incorrectly)");
            CollectionManager.getUsedId().remove(updatingHuman.getId());
            updatingHuman.setId(Long.parseLong(argName));
            collectionManager.remove(human);
            collectionManager.add(updatingHuman);
            return Text.getGreenText("Object has been updated!");
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

