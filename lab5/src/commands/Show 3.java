package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.Text;

public class Show extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager){
        super("show",
                "вывести в стандартный поток вывода " +
                        "все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) {
            for (HumanBeing human : collectionManager.getAllElements()) {
                System.out.println(human.toString());
            }
            System.out.println(CollectionManager.getUsedId());
            return Text.getGreenText("That's all there is in the collection!");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
