package crud.json;

import person.Person;

import java.io.*;
import java.util.ArrayList;

public class BinaryExecutor implements Executable {
    public void write(Person person) {
        try {
            OutputStream os = new FileOutputStream("resources/binary.dat", true);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> read() {
        ArrayList<Person> arrayList = new ArrayList<>();
        try {
            InputStream xz1 = new FileInputStream("resources/binary.dat");
            ObjectInputStream xz2 = new ObjectInputStream(xz1);
//            while (true) {
//                xz2.read();
//            }
//            arrayList =
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public boolean update(Person person) {
        return false;
    }

    public boolean delete(int id) {
        return false;
    }
}
