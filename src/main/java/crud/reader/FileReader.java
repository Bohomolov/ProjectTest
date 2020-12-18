package crud.reader;

import java.io.*;

public class FileReader {

    public void readFile(String fileName) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        int bit = 0;

        try {
            fileInputStream = new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");
            while ((bit = inputStreamReader.read()) != -1) {
                System.out.print((char) bit);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
