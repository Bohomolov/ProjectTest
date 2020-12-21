import crud.Executable;
import crud.json.*;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppController {

    private final Scanner scanner;
    private List<Person> arrayList;
    private Executable executor;


    public AppController() {
        scanner = new Scanner(System.in);

    }

    public void run() {
        arrayList = new ArrayList<>();
        while (true) {
            changeFormat();

            System.out.println("Enter command");

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("create")) {//итератор
                creator();
//              id должен быть уникальным
            } else if (command.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (command.equalsIgnoreCase("read")) {
                System.out.println("Enter file name");
                String fileName = scanner.nextLine();
                if (fileName.equalsIgnoreCase("switch")) {
                    changeFormat();
                }
                List<Person> personList = executor.read(fileName);
                for (Person p : personList) {
                    System.out.println(p);
                }

            } else if (command.equalsIgnoreCase("switch")) {
                changeFormat();
            } else if (command.equalsIgnoreCase("save")) {
                save(arrayList);
            } else if (command.equalsIgnoreCase("update")) {
                executor.update(arrayList);
            }else if (command.equalsIgnoreCase("delete")){
                System.out.println("Enter id");
                int id = scanner.nextInt();
                executor.delete(id,arrayList);
            }
        }
    }

    public void changeFormat() {
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
            default:
                System.out.println("Incorrect format.");
                break;
        }
    }

    private void creator() {
        System.out.println("Enter Person data: id , name, second name, age, city.");
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
        executor.write(fileName, arrayList);
    }
}
