package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.Text;

import java.util.Iterator;

public class Max_By_Real_Hero extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Max_By_Real_Hero(CollectionManager collectionManager){
        super("max_by_real_hero",
                "вывести любой объект из коллекции, " +
                        "значение поля realHero которого является максимальным");
        this.collectionManager =  collectionManager;
    }

    @Override
    public Object execute(String argName) {
        if (argName.equals("")) {
            if (collectionManager.size() != 0) {
                Iterator<HumanBeing> iterator = collectionManager.iterator();
                HumanBeing notHero = null;
                while (iterator.hasNext()) {
                    HumanBeing human = iterator.next();
                    if (human.isRealHero()) return human;
                    notHero = human;
                }
                return notHero;
            }
            return Text.getRedText("Collection is empty!");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
