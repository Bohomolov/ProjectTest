package crud.binary;

import crud.Executable;
import crud.fileUtils.Constants;
import crud.fileUtils.FileUtils;
import person.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.fileUtils.Constants.ENTER_PERSON_DATA_UPDATE;
import static crud.fileUtils.Constants.FILE_WAS_UPD;

public class BinaryExecutor implements Executable {
    private FileUtils fileUtils;
    private Scanner scanner;

   public BinaryExecutor(){
        fileUtils = new FileUtils();
        scanner =new Scanner(System.in);
    }

    public  boolean write(String fileName, List<Person> arrayList){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Person> read(String fileName){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Person> list = new ArrayList<>();
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Person>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Person> update(List<Person> arrayList,int id) {
        Iterator<Person> iterator = arrayList.iterator();

        System.out.println(ENTER_PERSON_DATA_UPDATE);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        while (iterator.hasNext()){

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id){
                iterPerson.setFirstName(array[0]);
                iterPerson.setLastName(array[1]);
                iterPerson.setAge(Integer.parseInt(array[2]));
                iterPerson.setCity(array[3]);
                break;
            }
        }
        System.out.println(FILE_WAS_UPD);
        return arrayList;
    }
    public List<Person> delete(int id,List<Person> arrayList){

        Iterator<Person> iterator = arrayList.iterator();

        while (iterator.hasNext()){

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id){
                iterator.remove();
                break;
            }
        }
        System.out.println(Constants.FILE_ELEMENT_WAS_DEL);
        return arrayList;
    }
}
