package lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @autor Kunakbaev Artem
 */
public class MyFileReader {
    private FileInputStream stream = null;
    private byte[] bytes = new byte[50];

    public MyFileReader() {
        try {
            stream = new FileInputStream(
                    "C:/Java/JavaCore3/edu/java/lesson03/50byte"); //как сделать путь покороче?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert stream != null;
            bytes = stream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert stream != null;
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void showBytes(){
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
}

