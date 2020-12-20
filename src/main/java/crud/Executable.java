package crud;

import person.Person;

import java.io.File;
import java.util.List;

public interface Executable {
    boolean write( String fileName, List<Person> arrayList); // create;

    //  List<Person> read(Person person); // read;
    List<Person> read(String fileName); // read; Person[] array;

    boolean update(Person person, String fileName); // update;

    boolean delete(int id); // delete
}
