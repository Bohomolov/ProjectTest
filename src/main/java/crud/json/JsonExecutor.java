package crud.json;

import com.google.gson.Gson;
import person.Person;

import java.util.ArrayList;

public class JsonExecutor implements Executable {
    private Gson gson;

    public JsonExecutor() {
        gson = new Gson().newBuilder().create();
    }

    public void write(Person person) {
        System.out.println("write to JSON file");
        gson.toJson(person);
    }

    public ArrayList<Person> read() {
        ArrayList<Person> arrayList = new ArrayList<Person>();
//        Person[] array = new Person[10];
//       arrayList = gson.fromJson();
        return arrayList;
    }

    public boolean update(Person newPerson) {
        ArrayList<Person> arrayList = read();
        for (Person oldPerson: arrayList) {
            if (newPerson.getId() == oldPerson.getId()) {
                oldPerson.setFirstName(newPerson.getFirstName());
                oldPerson.setLastName(newPerson.getLastName());
                oldPerson.setAge(newPerson.getAge());
                String newCityValue = newPerson.getCity();
                oldPerson.setCity(newCityValue);
                break;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        return false;
    }
}
