package lesson06;

import java.util.Arrays;

/**
 * @autor Kunakbaev Artem
 */
public class MassAfterNumber {
    private int findNumber;

    public MassAfterNumber(int number) {
        findNumber = number;
    }

    public int[] findMassAfterNumber(int[] mass) {
        int lastInd;
        lastInd = findLastIndex(mass);
        int lenghtNewMass;
        lenghtNewMass = mass.length - lastInd;
        int[] newMass = new int[lenghtNewMass];
        System.arraycopy(mass, lastInd, newMass, 0, newMass.length);
        return newMass;
    }

    private int findLastIndex(int[] mass) {
        int lastInd = 0;
        for (int i = 0; i < mass.length; i++) {
            lastInd = (mass[i] == findNumber & i > lastInd) ? i : lastInd;
        }
        if (lastInd == 0) throw new RuntimeException(findNumber + " отсутствует в массиве");
        return ++lastInd;
    }
}
