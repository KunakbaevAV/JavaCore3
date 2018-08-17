package lesson03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @autor Kunakbaev Artem
 */
class BookReader {
    private BufferedReader bufferedReader;
    private Scanner scanner;
    private String file;
    private StringBuilder str;

    BookReader(String file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileReader != null;//Почему мне Идея предлагает эту строчку?
        bufferedReader = new BufferedReader(fileReader);
        scanner = new Scanner(System.in);

        nextPage();
        changePage();
    }

    private void nextPage() {
        for (int i = 0; i < 30; i++) {
            try {
                System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void changePage() {

        String command;
        do {
            command = scanner.nextLine();
            if (command.equals("end")) break;
            if (command.equals("")) nextPage();
        } while (!command.equals("end"));

        scanner.close();
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

