package crud.json;

import person.Person;

import java.util.ArrayList;
import java.util.List;

public interface Executable {
    public abstract void write(Person person); // create;
//    public abstract List<Person> read(Person person); // read;
    public abstract ArrayList<Person> read(); // read; Person[] array;
    public abstract boolean update(Person person); // update;
    public abstract boolean delete(int id); // delete
}
