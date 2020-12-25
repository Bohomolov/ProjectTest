package crud.yaml;

import crud.Executable;
import crud.fileUtils.FileUtils;
import person.Person;

import java.util.List;
public class StringFormatExecutorYAML implements Executable {
    private FileUtils fileUtils;

    @Override
    public boolean write(String fileName, List<Person> arrayList) {
        return false;
    }

    @Override
    public List<Person> read(String fileName) {
        return null;
    }

    @Override
    public List<Person> update(List<Person> arrayList, int id) {
        return null;
    }

    @Override
    public List<Person> delete(int id, List<Person> arrayList) {
        return null;
    }
}
