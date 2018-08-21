package lesson04;

import java.io.IOException;

/**
 * @autor Kunakbaev Artem
 */
public class MFU implements MFUAbilities {
    private boolean isPrinting = false;
    private boolean isScaning = false;
    private final int rowInPage = 5;

    MFU() {
        Thread user1 = new Thread(new MFUUser(this, "Поток-1"));
        Thread user2 = new Thread(new MFUUser(this, "Поток-2"));
        Thread user3 = new Thread(new MFUUser(this, "Поток-3"));

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void printing(String str) {
        while (isPrinting) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isPrinting = true;

        doPrinting(str);

        isPrinting = false;
        notify();
    }

    @Override
    public synchronized void scaning(Thread scan, String nameThread) {
        while (isScaning) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isScaning = true;
        doScaning(scan, nameThread);
        isScaning = false;
        notify();
    }

    private void doPrinting(String str){
        String[] rows = str.split("\n");
        int count = 0;
        int numberPage = 1;

        for (String page : rows) {
            System.out.println(page);
            if (++count % rowInPage == 0){
                System.out.println("напечатано страниц: " + numberPage++);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScaning(Thread scan, String nameThread) {

        scan.start();
        try {
            scan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nameThread + " отсканировал документ!");
    }
}

class ScaningProcess implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Сканер: документ сканируется...");
                Thread.sleep(200);
            }
            System.out.println("Сканер: я отсканировал страницу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MFUUser implements Runnable {
    private MFU mfu;
    private StringBuilder text;
    private String nameThread;

    MFUUser(MFU mfu, String nameThread) {
        this.mfu = mfu;
        this.nameThread = nameThread;
        text = new StringBuilder();
        setText();
    }

    private void setText() {
        for (int i = 0; i < 100; i++) {
            this.text.append(nameThread).append(" ").append(i).append(" строчка в документе\n");
        }
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 3; i++) {//каждый пользователь МФУ печатает три документа
                mfu.printing(text.toString());
                Thread.sleep(200);
            }
            for (int i = 0; i < 2; i++) {//каждый пользователь сканирует два документа
                mfu.scaning(new Thread(new ScaningProcess()), nameThread);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}