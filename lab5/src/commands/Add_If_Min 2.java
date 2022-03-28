package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.CreatorHumanBeing;
import util.Text;

public class Add_If_Min extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final CreatorHumanBeing creatorHumanBeing;

    public Add_If_Min(CollectionManager collectionManager, CreatorHumanBeing creatorHumanBeing){
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, " +
                "чем у наименьшего элемента этой коллекции");
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
