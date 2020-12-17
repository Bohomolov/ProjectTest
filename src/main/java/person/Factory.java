package person;

import java.util.Scanner;

public class Factory {
    Scanner scanner = new Scanner(System.in);
    public Person create() {
        Person person = new Person();
        System.out.println("Set id");
        person.setId(scanner.nextInt());
        System.out.println("Set age");
        person.setAge(scanner.nextInt());
        System.out.println("Set name");
        person.setFirstName(scanner.nextLine());
        person.setFirstName(scanner.nextLine());
        System.out.println("Set last name");
        person.setLastName(scanner.nextLine());
        System.out.println("Set city");
        person.setCity(scanner.nextLine());
        return person;
    }
}
