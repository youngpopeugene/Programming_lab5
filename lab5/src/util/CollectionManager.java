package util;

import data.HumanBeing;

import java.util.*;
/**
 * Class to work with collection
 */
public class CollectionManager {
    private static TreeSet<data.HumanBeing> collection;
    private final String initTime;
    private static final String FILE_PATH = System.getenv("MY_PATH");
    private static List<Long> usedId = new ArrayList<>();

    public CollectionManager() {
        collection = new TreeSet<>();
        initTime = new Date().toString();
    }

    public static TreeSet<HumanBeing> getCollection() {
        return collection;
    }

    public String getInfo(){
        return "Type of collection" + " : " + "TreeSet" + "\n" +
                "Type of collection items" + " : " + "Humans" + "\n" +
                "Initialization date" + " : " + initTime + "\n" +
                "Number of items in the collection" + " : " + collection.size();
    }
    /**
     * Add element to collection
     *
     * @see commands.Add#execute
     * @see commands.Add_If_Min#execute
     */
    public void add(HumanBeing human){
        collection.add(human);
    }

    /**
     * Remove element from collection
     *
     * @see commands.Remove_By_Id#execute
     */
    public void remove(HumanBeing human){
        collection.remove(human);
    }

    /**
     * Remove all elements of collection
     *
     * @see commands.Clear#execute
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Method for return the element of collection by id
     *
     * @return the element of collection by id
     */
    public HumanBeing getById(Long id){
        for (HumanBeing human : collection){
            if (human.getId().equals(id)) return human;
        }
        return null;
    }

    public static List<Long> getUsedId() {
        return usedId;
    }

    public static String getFILE_PATH() {
        return FILE_PATH;
    }

    public Iterator<HumanBeing> iterator(){
        return collection.iterator();
    }

    public List<HumanBeing> getAllElements(){
        return new ArrayList<>(collection);
    }

    /**
     * Method for return the first element of collection
     *
     * @return the first element of collection
     */
    public HumanBeing first() {
        return collection.first();
    }

    /**
     * Method for return the size od collection
     *
     * @return the size od collection
     */
    public int size() {
        return collection.size();
    }
}
