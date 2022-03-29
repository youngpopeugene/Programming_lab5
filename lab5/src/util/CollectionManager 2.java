package util;

import data.HumanBeing;

import java.util.*;

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

    public void add(HumanBeing human){
        collection.add(human);
    }

    public void remove(HumanBeing human){
        collection.remove(human);
    }

    public void clear() {
        collection.clear();
    }

    public HumanBeing getId(Long id){
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

    public HumanBeing first() {
        return collection.first();
    }

    public int size() {
        return collection.size();
    }
}
