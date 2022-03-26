package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;

public class Update extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;
    public Update(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("update",
                "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.creatorHumanBeing = creatorHumanBeing;
    }

    @Override
    public Object execute(String argName) {
        if(isPositiveLong(argName)){
            HumanBeing human = collectionManager.getId(Long.parseLong(argName));
            if (human != null) collectionManager.remove(human);
            else return Text.getRedText("An object with this ID does not exist!");
            HumanBeing updatingHuman = creatorHumanBeing.createHumanBeing();
            updatingHuman.setId(Long.parseLong(argName));
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

