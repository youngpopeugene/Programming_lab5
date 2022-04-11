package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.Text;

import java.util.Iterator;

/**
 * Class for remove any element with the max realHero field
 */
public class Max_By_Real_Hero extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Max_By_Real_Hero(CollectionManager collectionManager){
        super("max_by_real_hero",
                "remove any object from the collection, " +
                        "whose realHero field value is the maximum");
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
