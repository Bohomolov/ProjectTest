package crud.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import crud.Executable;
import crud.fileUtils.Constants;
import crud.fileUtils.FileUtils;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.fileUtils.Constants.ENTER_PERSON_DATA_UPDATE;
import static crud.fileUtils.Constants.FILE_WAS_UPD;

public class StringFormatExecutorJSON implements Executable {
    private FileUtils fileUtils;
    private Scanner scanner;
    private IPersonStringConverter personStringConverter;

    public StringFormatExecutorJSON(IPersonStringConverter personStringConverter) {
        this.personStringConverter = personStringConverter;
        fileUtils = new FileUtils();
        scanner = new Scanner(System.in);
    }

    public boolean write(String fileName, List<Person> arrayList) throws JsonProcessingException {
        String content = personStringConverter.personToString(arrayList);
        arrayList.clear();
        return fileUtils.saveToFile(fileName, content);
    }

    public List<Person> read(String fileName) throws JsonProcessingException {
        String output = fileUtils.readFromFile(fileName);
        return personStringConverter.stringToPerson(output);
    }

    public List<Person> update(List<Person> arrayList, int id) {
        Iterator<Person> iterator = arrayList.iterator();

        System.out.println(ENTER_PERSON_DATA_UPDATE);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        while (iterator.hasNext()){

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id){
                iterPerson.setFirstName(array[0]);
                iterPerson.setLastName(array[1]);
                iterPerson.setAge(Integer.parseInt(array[2]));
                iterPerson.setCity(array[3]);
                break;
            }
        }
        System.out.println(FILE_WAS_UPD);
        return arrayList;
    }

    public List<Person> delete(int id, List<Person> arrayList) {

        Iterator<Person> iterator = arrayList.iterator();

        while (iterator.hasNext()){

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id){
                iterator.remove();
                break;
            }
        }
        System.out.println(Constants.FILE_ELEMENT_WAS_DEL);
        return arrayList;
    }
}