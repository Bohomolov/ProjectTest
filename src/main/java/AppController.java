import crud.Executable;
import crud.fileUtils.Constants;
import crud.fileUtils.FileUtils;
import crud.json.*;
import crud.string.impl.JsonStringConverter;
import person.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.fileUtils.Constants.*;

public class AppController {
    private final Scanner scanner;
    private List<Person> arrayList;
    private Executable executor;
    private FileUtils fileUtils;
    private String fileName;

    public AppController() {
        scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();
        fileUtils = new FileUtils();
    }

    public void run() {
        changeFormat();
        while (true) {
            System.out.println(ENTER_COMMAND);
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(CREATE)) {
                creator();
            } else if (command.equalsIgnoreCase(EXIT)) {
                System.exit(0);
            } else if (command.equalsIgnoreCase(READ)) {
                if (fileName.equalsIgnoreCase(SWITCH)) {
                    changeFormat();
                }
                if (!fileUtils.isFileEmpty(fileName)) {
                    arrayList = executor.read(fileName);
                } else {
                    System.out.println(FILE_IS_EMPTY);
                }
                for (Person p : arrayList) {
                    System.out.println(p);
                }

            } else if (command.equalsIgnoreCase(SWITCH)) {
                changeFormat();
            } else if (command.equalsIgnoreCase(SAVE)) {
                save(arrayList);
            } else if (command.equalsIgnoreCase(UPDATE)) {
                if (!fileUtils.isFileEmpty(fileName)) {
                    arrayList = executor.read(fileName);
                } else {
                    System.out.println(FILE_IS_EMPTY);
                }
                System.out.println(Constants.ENTER_ID);
                int id = scanner.nextInt();
                executor.update(arrayList, id);
            } else if (command.equalsIgnoreCase(DELETE)) {
                if (!fileUtils.isFileEmpty(fileName)) {
                    arrayList = executor.read(fileName);
                } else {
                    System.out.println(FILE_IS_EMPTY);
                }
                System.out.println(Constants.ENTER_ID);
                int id = scanner.nextInt();
                executor.delete(id, arrayList);
            }
        }
    }

    public void changeFormat() {
        System.out.println(Constants.ENTER_FILE_NAME);
        fileName = scanner.nextLine();
        fileUtils.createFile(fileName);
        System.out.println(Constants.CHOOSE_FORMAT);
        String newFormat = scanner.nextLine();
        switch (newFormat) {
//            case Constants.BINARY:
//                executor = new BinaryExecutor();
//                break;
            case Constants.JSON:
                executor = new StringFormatExecutorJSON(new JsonStringConverter());
                break;
//            case Constants.CSV:
//            executor = new CsvExecutor();
//            break;
//            case Constants.YAML:
//            executor = new YamlExecutor();
//            break;
//            case Constants.XML:
//            executor = new XmlExecutor();
//            break;
            default:
                System.out.println(Constants.INCORRECT_FORMAT);
                break;
        }
    }

    private void creator() {
        if (!fileUtils.isFileEmpty(fileName)) {
            arrayList = executor.read(fileName);

        } else {
            arrayList = new ArrayList<>();
        }
        System.out.println(Constants.ENTER_PERSON_DATA);
        String personData = scanner.nextLine();
        String[] array = personData.split(" ");
        Person person = new Person(
                Integer.parseInt(array[0]),
                array[1],
                array[2],
                Integer.parseInt(array[3]),
                array[4]);
        Iterator<Person> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Person iterPerson = iterator.next();
            if (iterPerson.getId() == person.getId()) {
                System.out.println(ILLEGAL_PERSON_ID);
                break;
            } else {
                arrayList.add(person);
                System.out.println(PERSON_WAS_CREATE);
            }
        }
    }

    private void save(List<Person> arrayList) {
        executor.write(fileName, arrayList);
        System.out.println(FILE_WAS_SAVE);
    }
}
