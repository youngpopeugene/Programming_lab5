package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.CollectionManager;
import util.Text;

import java.io.IOException;
import java.io.PrintWriter;

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
        try (PrintWriter printWriter = new PrintWriter(CollectionManager.getFILE_PATH())){
            gson.toJson(CollectionManager.getCollection(), printWriter);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Text.getRedText("IOException");
        }
        return Text.getGreenText("Collection has been saved!");
    }
}
