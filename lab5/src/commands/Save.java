package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.CollectionManager;
import util.Text;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Class to save collection in json file
 */
public class Save extends CommandAbstract {

    public Save(){
        super("save", "save collection to file");
    }

    @Override
    public Object execute(String argName) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try {
            File file = new File(CollectionManager.getFILE_PATH());
            if (!file.canWrite()) {
                return Text.getRedText("You don't have write permission!");
            }
        }catch(NullPointerException e){
            return Text.getRedText("Path to file doen't exist!");
        }


        try (PrintWriter printWriter = new PrintWriter(CollectionManager.getFILE_PATH())) {
            gson.toJson(CollectionManager.getCollection(), printWriter);
        } catch (IOException ex) {
            return Text.getRedText("Input or output error!");
        }
        return Text.getGreenText("Collection has been saved!");
    }
}
