package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;
/**
 * Class for add minimal element in collection
 */
public class Add_If_Min extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;

    public Add_If_Min(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("add_if_min", "add new element in collection if it's value is less " +
                "than value of the smallest element in collection");
        this.collectionManager = collectionManager;
        this.creatorHumanBeing = creatorHumanBeing;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")){
            HumanBeing human = creatorHumanBeing.createHumanBeing();
            if (human != null){
                if (human.compareTo(collectionManager.first()) < 0){
                    collectionManager.add(human);
                    return Text.getGreenText("Object has been added!");
                }
                CollectionManager.getUsedId().remove(human.getId());
                return Text.getRedText("Object hasn't been added! (condition not met)");
            }
            return Text.getRedText("Object hasn't been added! (fields entered incorrectly)");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
