package commands;

import data.HumanBeing;
import data.WeaponType;
import util.CollectionManager;
import util.Text;

import java.util.Iterator;

public class Count_Greater_Than_Weapon_Type extends CommandAbstract {
    private final CollectionManager collectionManager;

    public Count_Greater_Than_Weapon_Type(CollectionManager collectionManager){
        super("count_greater_than_weapon_type",
                "display the number of elements whose " +
                        "weaponType field value is greater than the given one");
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(String argName) {
        int count = 0;
        if(WeaponType.isIncludeElement(argName)){
            Iterator<HumanBeing> iterator = collectionManager.iterator();
            while (iterator.hasNext()){
                HumanBeing human = iterator.next();
                try {
                    if (human.getWeaponType().getWeaponType().compareTo(argName) > 0) count += 1;
                }catch(NullPointerException e){
                    human = iterator.next();
                }
            }
            return count;
        }
        return Text.getRedText("Command arguments entered incorrectly!");
    }
}
