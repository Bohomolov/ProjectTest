package crud;

import person.Person;

import java.util.List;

public interface Executable {
    boolean write( String fileName, List<Person> arrayList);

    List<Person> read(String fileName);

    List<Person> update(List<Person> arrayList,String fileName);

    List<Person> delete(int id,List<Person> arrayList);
}
