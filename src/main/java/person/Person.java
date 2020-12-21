package person;

import java.io.Serializable;

import java.util.List;

public class Person implements Serializable, Container {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private List<Person> arrayList;


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public Person(int id, String firstName, String lastName, int age, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    @Override
    public Iterator getIterator() {
        return new DataIterator();
    }

    private class DataIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            return (index < arrayList.size()) ? true : false;
        }

        @Override
        public Object next() {
            if (hasNext()) {
//                return arrayList[index];
            }
            return true;
        }
    }
}
