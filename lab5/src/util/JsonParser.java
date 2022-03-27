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
    public void jsonParse(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        HashMap<Long, String> dates = new HashMap<>();
        File file = new File(CollectionManager.getFILE_PATH());

        //проверка на существование файла
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(Text.getRedText(
                        "Insufficient permissions to create the file, or the path specified is incorrect!"));
            }
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
                        System.out.println(Text.getRedText("DateTimeParseException! Initial collection not installed!"));
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
                System.out.println(Text.getRedText("com.google.gson.JsonSyntaxException! Initial collection not installed!"));
            }
//            catch(NumberFormatException e){
//                System.out.println(Text.getRedText("NumberFormatException! Initial collection not installed!"));
//            }
//            catch(NullPointerException e){
//                System.out.println(Text.getRedText("NullPointerException! Initial collection not installed!"));
//            }
        } catch (IOException e) {
            System.err.println(Text.getRedText("Error reading file! Initial collection not installed!"));
        }
    }

}
