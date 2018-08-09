package lesson1;

import java.util.ArrayList;

/**
 * @autor Kunakbaev Artem
 */
public class Homework1 {
    public static void main(String[] args) {
        Box<Apple> appleBox6 = new Box<Apple>();
        for (int i = 0; i < 6; i++) { //кидаем в коробку 6 яблок
            appleBox6.addFruit(new Apple());
        }
        appleBox6.printWeight();

        Box<Orange> orangeBox6 = new Box<Orange>();
        for (int i = 0; i < 6; i++) {//кидаем в коробку 6 апельсинок
            orangeBox6.addFruit(new Orange());
        }
        orangeBox6.printWeight();

        appleBox6.printCompare(orangeBox6);//сравниваем

        Box<Apple> appleBox3 = new Box<Apple>();
        for (int i = 0; i < 3; i++) {//заполняем третью коробку с 3 яблоками
            appleBox3.addFruit(new Apple());
        }

        appleBox3.unloadFruits(appleBox6);//пересыпаем яблоки
        appleBox3.printWeight();//проверяем, опустела ли маленькая коробка

        appleBox6.printCompare(orangeBox6);//снова сравниваем большие коробки
    }
}

abstract class Fruit {
    private double mass;

    void setMass(double mass) {
        this.mass = mass;
    }

    double getMass() {
        return mass;
    }
}

class Apple extends Fruit {
    Apple() {
        super.setMass(1);
    }
}

class Orange extends Fruit {
    Orange() {
        super.setMass(1.5);
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    Box() {
        fruits = new ArrayList<T>();
    }

    void addFruit(T fruit) {
        fruits.add(fruit);
    }

    private double getWeight() {
        double weight = 0.0;
        for (Fruit f : fruits) {
            weight += f.getMass();
        }
        return weight;
    }

    void printWeight() {
        System.out.println("Вес коробки нетто = " + getWeight() + " килограмм.");
    }

    private boolean compare(Box<?> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    void printCompare(Box<?> anotherBox) {
        System.out.println(compare(anotherBox) ? "Коробки весят одинаково" : "Вес коробок разный");
    }

    void unloadFruits(Box<T> boxLoad) {
        for (int i = this.fruits.size() - 1; i >= 0; i--) {
            boxLoad.addFruit(boxLoad.fruits.get(i));
            this.fruits.remove(i);
        }
    }
}