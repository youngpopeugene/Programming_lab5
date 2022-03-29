package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.HumanBeing;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class JsonParser {
    private Scanner scanner;
    public JsonParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public Object jsonParse(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        HashMap<Long, String> dates = new HashMap<>();

        try {
            File file = new File(CollectionManager.getFILE_PATH());
            //проверка на существование файла
            if (!file.exists()) {
                file.createNewFile();
                return Text.getGreenText("Empty file for writing has been created! Collection is empty!");
            }else{
                if (!file.canWrite()) changeWritePermissions(file);
                if (!file.canRead()) {
                    if (!changeReadPermissions(file))
                        return Text.getBlueText("Unable to change read permissions! Collection is empty!");
                }
            }
        }catch (IOException e) {
            return Text.getRedText(
                    "Insufficient permissions to create the file, or the path specified is incorrect!");
        }

        try(Reader inputStreamReader = new InputStreamReader(new FileInputStream(CollectionManager.getFILE_PATH()))) {
            int data = 0;
            StringBuilder inputStr = new StringBuilder();
            while(data != -1) {
                data = inputStreamReader.read();
                char chr = (char) data;
                String charToString = Character.toString(chr);
                inputStr.append(charToString);
            }
            inputStr = new StringBuilder(inputStr.substring(0, inputStr.length() - 1));

            Scanner scanner = new Scanner(String.valueOf(inputStr)).useDelimiter("\\A");
            String json = scanner.hasNext() ? scanner.next() : "";
            try {
                String[] splitJson = json.split("\n");

                TreeSet<HumanBeing> collection =
                        gson.fromJson(json.toString(), new TypeToken<TreeSet<HumanBeing>>() {}.getType());

                //замена повторяющихся id
                int i = -1;
                for (String str : splitJson){
                    i++;
                    if (str.matches("[ \t\n\\x0B\f\r]* \"id\": .*,")) {
                        String a = str.split(":")[1];
                        String b = a.split(" ")[1]; //для нахождения в строке ID
                        String c = b.split(",")[0];
                        if (!CollectionManager.getUsedId().contains(Long.parseLong(c))) {
                            CollectionManager.getUsedId().add(Long.parseLong(c));
                        }
                        else{
                            Long modifiedId = 1L;
                            while(CollectionManager.getUsedId().contains(modifiedId)){
                                modifiedId++;
                            }
                            str = str.replace(c, String.valueOf(modifiedId));
                            splitJson[i] = str;
                            CollectionManager.getUsedId().add(modifiedId);
//                            System.out.println(splitJson[i]);
                        }
                    }
                }

                // Нахождение creationDate и ID и добавление в hashmap
                Long jsonId = (long) -1;
                for (String str : splitJson) {
                    if (str.matches("[ \t\n\\x0B\f\r]* \"id\": .*,")) {
                        String a = str.split(":")[1];
                        String b = a.split(" ")[1];
                        jsonId = Long.parseLong(b.split(",")[0]);
                    }
                    if (str.matches("[ \t\n\\x0B\f\r]* \"creationDate\": \".*\",")) {
                        dates.put(jsonId, str.split("\"")[3]);
                    }
                }

                // новый splitJson вследствие замены id
                StringBuilder newJson = new StringBuilder();
                for (String str : splitJson) {
                    newJson.append(str);
                    newJson.append("\n");
                }

                collection =
                        gson.fromJson(newJson.toString(), new TypeToken<TreeSet<HumanBeing>>() {}.getType());

                // установка creationDate
                for (HumanBeing human : collection) {
                    try {
                        human.setCreationDate(LocalDateTime.parse(dates.get(human.getId())));
                        human.setCreationDateStr(dates.get(human.getId()));
                    }
                    catch (DateTimeParseException e) {
                        return Text.getRedText("DateTimeParseException! Initial collection not installed!");
                    }
                }

//                System.out.println(collection);

                // добавление объектов в коллекцию
                for (HumanBeing human : collection) {
                    if (human.validation()) {
                        CollectionManager.getCollection().add(human);
                    }else{
                        CollectionManager.getUsedId().remove(human.getId());
                    }
                }

            }
            catch(com.google.gson.JsonSyntaxException e){
                return Text.getRedText("com.google.gson.JsonSyntaxException! Initial collection not installed!");
            }
        } catch (IOException e) {
            return Text.getRedText("Error reading file! Initial collection not installed!");
        }

        return Text.getGreenText("Collection from file has been parsed");
    }

    public void changeWritePermissions(File file) {
        System.out.println(Text.getRedText("Cannot write file! You can't save data if you will to want!"));
        System.out.println(Text.getBlueText("Try to change permissions? [Y/N] "));
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("Yes")) {
                if (!file.setWritable(true)) {
                    System.out.println(Text.getRedText("Failed to change permissions!"));
                } else System.out.println(Text.getGreenText("Permissions changed successfully!"));
            } else if (!input.equals("N") && !input.equals("No")) System.out.println(Text.getRedText("Please, write 'Yes' or 'No [Y/N] "));
        } while (!input.equals("Y") && !input.equals("N") && !input.equals("Yes") && !input.equals("No"));
    }

    public boolean changeReadPermissions(File file) {
        System.out.println(Text.getRedText("Cannot read file!"));
        System.out.println(Text.getBlueText("Try to change permissions? [Y/N] "));
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("Yes")) {
                if (!file.setReadable(true)) {
                    System.out.println(Text.getRedText("Failed to change permissions"));
                    return false;
                }
                else {
                    System.out.println(Text.getGreenText("Permissions changed successfully!"));
                    return true;
                }
            } else if (input.equals("N") || input.equals("No")) return false;
            else System.out.println(Text.getBlueText("Please, write 'Yes' or 'No' [Y/N]: "));
        } while (true);
    }


}
