package lesson05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @autor Kunakbaev Artem
 */
class CarsPrepare {
    private final List<Car> cars;

    CarsPrepare(int carCount, Race race, CountDownLatch startLine, CountDownLatch finishLine) {

        cars = new ArrayList<>();
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car(race, 20 + (int) (Math.random() * 10), startLine, finishLine));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
    }

    void getResults(){
        for (Car car : cars) {
            System.out.println(car.getPosition());
        }
    }
}
