package lesson06;

/**
 * @autor Kunakbaev Artem
 */
public class MassFromNumbers {
    private int firstNum;
    private int secondNum;

    public MassFromNumbers(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public boolean isMassValid(int[] mass){
        for (int num : mass) {
            if(num != firstNum && num != secondNum) return false;
        }
        return true;
    }
}
