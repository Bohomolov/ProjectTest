import crud.json.*;
import crud.reader.FileReader;
import person.Person;

import java.util.Scanner;

public class Client {
    CreatorJSONFiles creatorFiles;
    FileReader fileReader = new FileReader();
    Scanner scanner = new Scanner(System.in);

    private Executable executor;

    public Client() {
        this.creatorFiles = new CreatorJSONFiles();
    }



    public void run(){

        while (true) {
            changeFormat();

            System.out.println("Enter command");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("create")) {//итератор
                xz();
//                creatorFiles.createJSONFile(fileName, personFactory.create());//id должен быть уникальным
            } else if (command.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (command.equalsIgnoreCase("read")) {
                System.out.println("Enter file name");
                String fileName = scanner.nextLine();
                fileReader.readFile(fileName);

            } else if (command.equalsIgnoreCase("switch")) {
                changeFormat();
            }
        }
    }

    private void changeFormat() {
        System.out.println("Choose format");
        String newFormat = scanner.nextLine();
        switch (newFormat) {
            case "binary": executor = new BinaryExecutor(); break;
            case "json": executor = new JsonExecutor(); break;
//            case "csv": executor = new CsvExecutor(); break;
//            case "yaml": executor = new YamlExecutor(); break;
//            case "xml": executor = new XmlExecutor(); break;
        }
    }

    private void xz() {
        System.out.println("Enter Person data");
        String personData = scanner.nextLine(); // 1 Vasya Pupkin 23 Kharkov
        String[] array = personData.split(" "); // ["1", "Vasya", "Pupkin", "23", "Kharkov"]
        Person person = new Person(Integer.parseInt(array[0]),
                array[1], array[2], Integer.parseInt(array[3]), array[4]);
        executor.write(person);
    }
}
