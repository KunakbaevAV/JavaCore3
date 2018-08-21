package lesson04;

/**
 * @autor Kunakbaev Artem
 */
public class PrintABC implements printing {

    private int counter = 3;

    PrintABC() {
        Thread threadA = new Thread(new PrintA(this));
        Thread threadB = new Thread(new PrintB(this));
        Thread threadC = new Thread(new PrintC(this));

        threadA.start();
        threadB.start();
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void printA() {
        while (counter % 3 != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        counter++;
        notifyAll();
    }

    @Override
    public synchronized void printB() {
        while (counter % 3 != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        counter++;
        notifyAll();
    }

    @Override
    public synchronized void printC() {
        while (counter % 3 != 2)try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("C");
        counter++;
        notifyAll();
    }
}

class PrintA implements Runnable {

    private PrintABC printABC;

    PrintA(PrintABC printABC) {
            this.printABC = printABC;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            printABC.printA();
        }
    }
}

class PrintB implements Runnable {

    private PrintABC printABC;

    PrintB(PrintABC printABC) {
        this.printABC = printABC;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            printABC.printB();
        }
    }
}

class PrintC implements Runnable {

    private PrintABC printABC;

    PrintC(PrintABC printABC) {
        this.printABC = printABC;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            printABC.printC();
        }
    }
}

