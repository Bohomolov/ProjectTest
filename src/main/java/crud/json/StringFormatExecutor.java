package crud.json;

import crud.Executable;
import crud.fileUtils.FileUtils;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.Iterator;
import java.util.List;

public class StringFormatExecutor implements Executable {
    private FileUtils fileUtils;
    private IPersonStringConverter personStringConverter;

    public StringFormatExecutor(IPersonStringConverter personStringConverter) {
        this.personStringConverter = personStringConverter;
        fileUtils = new FileUtils();
    }

    public boolean write(String fileName, List<Person> arrayList) {
        String content = personStringConverter.personToString(arrayList);
        arrayList.clear();
        return fileUtils.saveToFile(fileName, content);
    }

    public List<Person> read(String fileName) {
        String output = fileUtils.readFromFile(fileName);
        return personStringConverter.stringToPerson(output);
    }


    public List<Person> update(List<Person> arrayList, String fileName) {
        List<Person> tmpList = read(fileName);
        arrayList.addAll(tmpList);
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
        return arrayList;
    }
}
