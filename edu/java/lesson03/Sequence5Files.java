package lesson03;

import java.io.*;
import java.util.*;

/**
 * @autor Kunakbaev Artem
 */
public class Sequence5Files {

    public Sequence5Files() {
        List<FileInputStream> list = new ArrayList<>();
        SequenceInputStream stream = null;
        try {
            FileInputStream file1 = new FileInputStream("C:/Java/JavaCore3/edu/java/lesson03/File1");
            FileInputStream file2 = new FileInputStream("C:/Java/JavaCore3/edu/java/lesson03/File2");
            FileInputStream file3 = new FileInputStream("C:/Java/JavaCore3/edu/java/lesson03/File3");
            FileInputStream file4 = new FileInputStream("C:/Java/JavaCore3/edu/java/lesson03/File4");
            FileInputStream file5 = new FileInputStream("C:/Java/JavaCore3/edu/java/lesson03/File5");

            list.add(file1);
            list.add(file2);
            list.add(file3);
            list.add(file4);
            list.add(file5);

            stream = new SequenceInputStream(Collections.enumeration(list));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
                    "C:/Java/JavaCore3/edu/java/lesson03/FileAll"));
            int b;
            assert stream != null;
            while ((b = stream.read()) != -1) {
                outputStream.write(b);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

