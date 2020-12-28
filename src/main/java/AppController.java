import com.fasterxml.jackson.core.JsonProcessingException;
import crud.Executable;
import crud.binary.BinaryExecutor;
import crud.fileUtils.Constants;
import crud.fileUtils.FileUtils;
import crud.json.*;
import crud.string.impl.JsonStringConverter;
import crud.string.impl.YamlStringConverter;
import crud.yaml.StringFormatExecutorYAML;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static crud.fileUtils.Constants.*;

public class AppController {
    private final Scanner scanner;
    private List<Person> arrayList;
    private List<Person> tempList;
    private Executable executor;
    private FileUtils fileUtils;
    private String fileName;

    public AppController() {
        scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();
        fileUtils = new FileUtils();
    }

    public void run() throws JsonProcessingException {
        changeFormat();
        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(CREATE)) {
                creator();
                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(EXIT)) {
                System.exit(0);

            } else if (command.equalsIgnoreCase(READ)) {

                if (fileName.equalsIgnoreCase(SWITCH)) {
                    changeFormat();
                } else if (!fileUtils.isFileEmpty(fileName)) {
                    tempList = executor.read(fileName);
                    for (Person p : tempList) {
                        System.out.println(p);
                    }
                } else {
                    System.out.println(FILE_IS_EMPTY);
                }
                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(SWITCH)) {
                changeFormat();
                System.out.println(ENTER_COMMAND);

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

                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(DELETE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    arrayList = executor.read(fileName);
                    System.out.println(Constants.ENTER_ID);
                    int id = scanner.nextInt();
                    executor.delete(id, arrayList);

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(ENTER_COMMAND);
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
            case Constants.BINARY:
                executor = new BinaryExecutor();
                break;
            case Constants.JSON:
                executor = new StringFormatExecutorJSON(new JsonStringConverter());
                break;
//            case Constants.CSV:
//            executor = new CsvExecutor();
//            break;
            case Constants.YAML:
            executor = new StringFormatExecutorYAML(new YamlStringConverter());
            break;
//            case Constants.XML:
//            executor = new XmlExecutor();
//            break;
            default:
                System.out.println(Constants.INCORRECT_FORMAT);
                break;
        }

        System.out.println(ENTER_COMMAND);
    }

    private void creator() throws JsonProcessingException {
        System.out.println(Constants.ENTER_PERSON_DATA);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        Person person = new Person(
                Integer.parseInt(array[0]),
                array[1],
                array[2],
                Integer.parseInt(array[3]),
                array[4]);

        if (!fileUtils.isFileEmpty(fileName)) {

            arrayList = executor.read(fileName);

            if (fileUtils.isIdLegal(arrayList, person)) {
                arrayList.add(person);//баг с множественным созданием. надо фиксить!!

                System.out.println(PERSON_WAS_CREATE);

            }
        } else {

            arrayList.add(person);

            System.out.println(PERSON_WAS_CREATE);
        }
    }

    private void save(List<Person> arrayList) throws JsonProcessingException {
        executor.write(fileName, arrayList);
        System.out.println(FILE_WAS_SAVE);
        System.out.println(ENTER_COMMAND);
    }
}
