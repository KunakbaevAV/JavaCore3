package lesson06;

/**
 * @autor Kunakbaev Artem
 */
public class App {
    public static void main(String[] args) {
        MassAfterNumber mass = new MassAfterNumber(4);//первое задание
//        System.out.println(Arrays.toString(mass.findMassAfterNumber(new int[]{1, 2, 3, 5, 6, 7})));
//        System.out.println(Arrays.toString(mass.findMassAfterNumber(new int[]{1, 2, 3, 4, 5, 6, 7})));
//        System.out.println(Arrays.toString(mass.findMassAfterNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})));
//        System.out.println(Arrays.toString(mass.findMassAfterNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})));

        MassFromNumbers mass2 = new MassFromNumbers(1,4);//второе задание
//        System.out.println(mass2.isMassValid(new int[]{1,4,1,1,4,1}));
//        System.out.println(mass2.isMassValid(new int[]{1,4,1,1,4,1,3}));

        new BaseWindow(new BaseStudents());//третье задание
    }
}
