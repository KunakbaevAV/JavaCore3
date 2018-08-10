package lesson01;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @autor Kunakbaev Artem
 */
public class Homework2 {
    public static void main(String[] args) {

        //первое задание
        Mass<String> mass = new Mass<String>("first", "second", "third");
        mass.printMass();
        mass.changeElements(0,1);
        System.out.println();
        mass.printMass();

        //второе задание
        mass.toArrayList();

        System.out.println(mass.getMass().length);//тут массив
        System.out.println(mass.toArrayList().size());//тут ArrayList

    }

    static class Mass<T>{
        private T[] mass;

        Mass(T ... mass) {
            this.mass = mass;
        }

        public void setMass(T[] mass) {
            this.mass = mass;
        }

        T[] getMass() {
            return mass;
        }

        void changeElements(int firstElem, int secondElem){//метод, меняющий местами элементы массива
            try {
                T temp = mass[secondElem];
                mass[secondElem] = mass[firstElem];
                mass[firstElem] = temp;
            } catch (Exception e) {
                System.out.println("Указаны неверные значения индексов");
            }
        }

        ArrayList<T> toArrayList(){
            ArrayList<T> list = new ArrayList<T>();
            Collections.addAll(list, mass);
            return list;
        }

        void printMass(){
            for (T element : mass) {
                System.out.print(element + " ");
            }
            System.out.println("");
        }
    }
}

