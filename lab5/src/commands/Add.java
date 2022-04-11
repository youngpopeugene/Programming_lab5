package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;
/**
 * Class for read study group from console and add this in collection
 */
public class Add extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;

    public Add(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("add", "add new element in collection");
        this.collectionManager = collectionManager;
        this.creatorHumanBeing = creatorHumanBeing;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")){
            HumanBeing human = creatorHumanBeing.createHumanBeing();
            if (human != null){
                for (HumanBeing otherHuman : collectionManager.getAllElements()) {
                    if (human.getName().equals(otherHuman.getName())) {
                        CollectionManager.getUsedId().remove(human.getId());
                        return Text.getRedText("Object hasn't been added! (human with this name already exist)");
                    }
                }
                collectionManager.add(human);
                return Text.getGreenText("Object has been added!");
            }
            return Text.getRedText("Object hasn't been added! (fields entered incorrectly)");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
