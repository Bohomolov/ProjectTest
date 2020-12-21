package crud.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import crud.Executable;
import person.Person;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class JsonExecutor implements Executable {
    private final Gson gsonBuilder;
    private Gson gson;
    private FileWriter fileWriter;
    private Scanner scanner;
    private List<Person> arrayList;
    private FileInputStream fileInputStream = null;
    private InputStreamReader inputStreamReader = null;

    public JsonExecutor() {
        gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        gson = new Gson();
        fileWriter = null;
        scanner = new Scanner(System.in);
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
        int bit = 0;
        String output = "";
        try {
            fileInputStream = new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");
            while ((bit = inputStreamReader.read()) != -1) {
                output += (char) bit;
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

    public List<Person> update(List<Person> arrayList) {

        System.out.println("Enter file name");
        String fileName = scanner.nextLine();
        List<Person> tmpList = read(fileName);
        arrayList.addAll(tmpList);
        return arrayList;
    }

    public boolean delete(int id, List<Person> arrayList) {
        arrayList.remove(id);
        return false;
    }
}
