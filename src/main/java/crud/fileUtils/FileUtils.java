package crud.fileUtils;

import java.io.*;

public class FileUtils {

    public boolean saveToFile(String fileName, String content) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try (FileWriter fileWriter = new FileWriter(file,false)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String readFromFile(String fileName) {
        int bit = 0;
        String output = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8")) {
            while ((bit = inputStreamReader.read()) != -1) {
                output += (char) bit;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}