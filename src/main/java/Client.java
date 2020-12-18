import crud.json.ReaderFromJSON;
import crud.reader.FileReader;
import crud.json.CreatorJSONFiles;
import person.Factory;

import java.util.Scanner;

public class Client {
    CreatorJSONFiles creatorFiles = new CreatorJSONFiles();
    Factory personFactory = new Factory();
    FileReader fileReader = new FileReader();
    Scanner scanner = new Scanner(System.in);
    ReaderFromJSON readerJSON = new ReaderFromJSON();

    public void run(){

        while (true) {

            System.out.println("Enter command");
            String command = scanner.nextLine();

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
