package lesson05;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @autor Kunakbaev Artem
 */
public class Car implements Runnable {
    private static int CARS_COUNT;
    private CountDownLatch startLine;
    private CountDownLatch finishLine;

    private final Prize prize;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    String getPosition(){
        return prize.getPosition(this);
    }

    String getName() {
        return name;
    }

    int getSpeed() {
        return speed;
    }

    Car(Race race, int speed, CountDownLatch startLine, CountDownLatch finishLine) {
        this.race = race;
        this.speed = speed;
        this.startLine = startLine;
        this.finishLine = finishLine;
        prize = new Prize();
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startLine.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            startLine.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        synchronized (prize){
            prize.carFinish(this);
        }
        finishLine.countDown();
    }

    private static class Prize {
        private static int position = 0;
        private String positionString;
        private HashMap<Car, String> winners = new HashMap<>();

        private void carFinish (Car car) {
            position++;
            positionString = car.name + " занял " + position + " место";
            winners.put(car, positionString);
        }

        private String getPosition(Car car){
            return winners.get(car);
        }
    }
}


