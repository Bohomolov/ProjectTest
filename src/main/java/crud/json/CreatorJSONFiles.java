package crud.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import person.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreatorJSONFiles {
    public void createJSONFile(String fileName, Person persons) {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file,true);
            fileWriter.write(gsonBuilder.toJson(persons) + "\n");

        } catch (IOException e) {
            System.out.println("Error" + e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
