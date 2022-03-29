package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;

import java.util.Iterator;

public class Remove_Lower extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;

    public Remove_Lower(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("remove_lower",
                "remove from the collection all elements smaller than the given one");
        this.collectionManager =  collectionManager;
        this.creatorHumanBeing = creatorHumanBeing;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")){
            HumanBeing humanToCompare = creatorHumanBeing.createHumanBeing();
            if (humanToCompare != null){
                Iterator<HumanBeing> iterator = collectionManager.iterator();
                while (iterator.hasNext()){
                    HumanBeing human = iterator.next();
                    if (human.compareTo(humanToCompare) < 0) {
                        iterator.remove();
                        CollectionManager.getUsedId().remove(human.getId());
                    }
                }
                CollectionManager.getUsedId().remove(humanToCompare.getId());
                return Text.getGreenText("All lower elements have been removed!");
            }
            return Text.getRedText("Fields entered incorrectly!");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
