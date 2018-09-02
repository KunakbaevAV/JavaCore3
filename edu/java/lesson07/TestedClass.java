package lesson07;

import lesson07.annotations.AfterSuite;
import lesson07.annotations.BeforeSuite;
import lesson07.annotations.Test;

/**
 * @autor Kunakbaev Artem
 */
public class TestedClass {
    @BeforeSuite
    public void before(){
        System.out.println("Перед тестом...");
    }

    @AfterSuite
    public void after(){
        System.out.println("После теста...");
    }

    @Test(priority = 2)
    public void test1(){
        System.out.println("Тест 1, приоритет = 2");
    }

    @Test(priority = 2)
    public void test2(){
        System.out.println("Тест 2, приоритет = 2");
    }

    @Test(priority = 1)
    public void test3(){
        System.out.println("Тест 3, приоритет = 1");
    }

    @Test
    public void test4(){
        System.out.println("Тест 4, приоритет = по умолчанию");
    }

    @Test
    public void test5(){
        System.out.println("Тест 5, приоритет = по умолчанию");
    }

//    @AfterSuite
//    public void test6(){
//        System.out.println("2 раз после теста...");
//    }
}
