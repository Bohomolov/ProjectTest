package crud.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import crud.Executable;
import person.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonExecutor implements Executable {
    private final Gson gsonBuilder;
    private Gson gson;
    private FileWriter fileWriter;
    private ArrayList<Person> arrayList;


    public JsonExecutor() {
        gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        gson = new Gson();
        fileWriter = null;
    }

    public boolean write(String fileName, List<Person> arrayList) {
        boolean flag = false;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(gsonBuilder.toJson(arrayList));
            flag = true;
        } catch (
                IOException e) {
            System.out.println("Error" + e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public List<Person> read(String fileName) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        int bit = 0;
        String output = "";
        try {
            fileInputStream = new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");
            while ((bit = inputStreamReader.read()) != -1) {
                output+=(char) bit;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gson.fromJson(output, new TypeToken<List<Person>>() {
        }.getType());
    }

    public boolean update(Person newPerson, String fileName) {
        ArrayList<Person> arrayList = (ArrayList<Person>) read(fileName);
        for (Person oldPerson : arrayList) {
            if (newPerson.getId() == oldPerson.getId()) {
                oldPerson.setFirstName(newPerson.getFirstName());
                oldPerson.setLastName(newPerson.getLastName());
                oldPerson.setAge(newPerson.getAge());
                String newCityValue = newPerson.getCity();
                oldPerson.setCity(newCityValue);
                break;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        return false;
    }
}
