import crud.FileReader;
import crud.json.CreatorJSONFiles;
import person.Factory;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public void run() throws IOException {
        while (true) {
            System.out.println("Enter command");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            CreatorJSONFiles creatorFiles = new CreatorJSONFiles();
            Factory personFactory = new Factory();
            FileReader fileReader = new FileReader();

            if (command.equalsIgnoreCase("create")) {
                System.out.println("Enter file name");
                String fileName = scanner.nextLine();
                creatorFiles.createJSONFile(fileName, personFactory.create());
            } else if (command.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (command.equalsIgnoreCase("read")) {
                System.out.println("Enter file name");
                String fileName = scanner.nextLine();
                fileReader.readFile(fileName);
            }
        }
    }
}
