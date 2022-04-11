package commands;

import data.HumanBeing;
import util.CollectionManager;
import util.Text;

import java.util.Iterator;
/**
 * Class for print elements which have specific impact speed
 */
public class Filter_By_Impact_Speed extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Filter_By_Impact_Speed(CollectionManager collectionManager){
        super("filter_by_impact_speed",
                "display elements whose value of the impactSpeed field is equal to the given one");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        boolean isArgNull = false;
        int check = 0;

        if (isFloat(argName)){
            if (collectionManager.size() != 0) {
                Iterator<HumanBeing> iterator = collectionManager.iterator();
                while (iterator.hasNext()) {
                    HumanBeing human = iterator.next();
                    try {
                        if (human.getImpactSpeed() == Float.parseFloat(argName)) {
                            System.out.println(human);
                            check += 1;
                        }
                    }catch(NullPointerException e){
                        human = iterator.next();
                    }
                }
                if (check != 0) return Text.getGreenText("Successfully filtered by impact speed!");
                return Text.getRedText("No one has the same impact speed :(");
            }
            return Text.getRedText("Collection is empty!");
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }

    public boolean isFloat(String line){
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}


