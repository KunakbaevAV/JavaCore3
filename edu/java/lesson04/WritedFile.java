package lesson04;

import java.io.*;

/**
 * @autor Kunakbaev Artem
 */
class WritedFile {
    private static final String fileDir = "./src/resurces/MyFile.txt";
    private boolean isPrinting = false;

    WritedFile() {
        Thread writer1 = new Thread(new stringWriter(this));
        Thread writer2 = new Thread(new stringWriter(this));
        Thread writer3 = new Thread(new stringWriter(this));

        writer1.start();
        writer2.start();
        writer3.start();

        try {
            writer1.join();
            writer2.join();
            writer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readFile();
    }

    synchronized void writeString(String str) {

        if(isPrinting){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            isPrinting = true;
            try (PrintWriter out = new PrintWriter(new FileWriter(fileDir, true))) {
                out.write(str);
                out.write("\n");
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            isPrinting = false;
            notify();
        }
    }

    private void readFile() {
        try (BufferedReader in = new BufferedReader(new FileReader(fileDir))) {

            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class stringWriter implements Runnable {
    private WritedFile writedFile;

    stringWriter(WritedFile writedFile) {
        this.writedFile = writedFile;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            writedFile.writeString(Thread.currentThread().getName() + " " + (i+1) + " запись");
        }
    }
}

