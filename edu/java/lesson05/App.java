package lesson05;

import java.util.concurrent.CountDownLatch;

/**
 * @autor Kunakbaev Artem
 */
public class App {
    private static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CountDownLatch startLine = new CountDownLatch(CARS_COUNT);
        CountDownLatch finishLine = new CountDownLatch(CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        CarsPrepare allCars = new CarsPrepare(CARS_COUNT, race, startLine, finishLine);

        try {
            startLine.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            finishLine.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            allCars.getResults();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
