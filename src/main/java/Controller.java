import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crud.Executable;
import crud.json.*;
import tmp.FileReader;
import person.Person;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final FileReader fileReader;
    private final Scanner scanner;
    private ArrayList<Person> arrayList;
    private Executable executor;
    private Gson gsonBuilder;
    private FileWriter fileWriter;

    public Controller() {
        scanner = new Scanner(System.in);
        fileReader = new FileReader();
        gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        fileWriter = null;
    }

    public void run() {
        arrayList = new ArrayList<>();
        while (true) {
            changeFormat();// надо переделать

            System.out.println("Enter command");

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("create")) {//итератор// команда создает колекцию персон. каждый раз когда юзер её использует в колекцию добавляеться новая персона. надо использовать сейв что бы записать файл
                creator();
//              id должен быть уникальным-это я для себя
            } else if (command.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (command.equalsIgnoreCase("read")) {//считывает указаный файл и выводит его содержимое в консоль
                System.out.println("Enter file name");
                String fileName = scanner.nextLine();
                List<Person> personList = executor.read(fileName);
                for (Person p : personList) {
                    System.out.println(p);
                }

            } else if (command.equalsIgnoreCase("switch")) {// пока нормально не неализована
                changeFormat();
            } else if (command.equalsIgnoreCase("save")) { // сохраняет текущую колекцию в файл. файл если не создан то создаеться и файл перезаписываеться полностью
                save(arrayList);
            }else if (command.equalsIgnoreCase("update")){// вычитывает нужный файл превращая его в колекцию и добавляет в него колекцию та что только создана. после апдейта надо использовать сейв
                update(arrayList);
            }
        }
    }// пока всё

    private void changeFormat() {
        System.out.println("Choose format");
        String newFormat = scanner.nextLine();
        switch (newFormat) {
//            case "binary":
//                executor = new BinaryExecutor();
//                break;
            case "json":
                executor = new JsonExecutor();
                break;
//            case "csv":
//            executor = new CsvExecutor();
//            break;
//            case "yaml":
//            executor = new YamlExecutor();
//            break;
//            case "xml":
//            executor = new XmlExecutor();
//            break;
        }
    }

    private void creator() {
        System.out.println("Enter Person data");
        String personData = scanner.nextLine();
        String[] array = personData.split(" ");
        Person person = new Person(
                Integer.parseInt(array[0]),
                array[1],
                array[2],
                Integer.parseInt(array[3]),
                array[4]);
        arrayList.add(person);
    }

    private void save(List<Person> arrayList) {
        System.out.println("Enter file name");
        String fileName = scanner.nextLine();
        executor.write(fileName,arrayList);
    }
    private List<Person> update(List<Person> arrayList){
        System.out.println("Enter file name");
        String fileName = scanner.nextLine();
        List<Person> tmpList = executor.read(fileName);
        arrayList.addAll(tmpList);
        return arrayList;
    }
}
