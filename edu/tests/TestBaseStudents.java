import lesson06.BaseStudents;
import lesson06.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @autor Kunakbaev Artem
 */
public class TestBaseStudents {
    private BaseStudents base;
    private Student student;

    @Before
    public void testBefore(){
        base = new BaseStudents();
        base.createNewBase();//на случай, если это первый запуск программы
        student = new Student("testVasia", 5);
        base.insert(student);
        student.setId(base.getIdToName(student.getName()));//записать в студента автоматически присвоенный ID
    }

    @Test
    public void testInsertAndRead(){
        Student actual = base.get(student.getId());
        System.out.println("Первая проверка. Проверяемое значение: " + student.toString());
        System.out.println("Первая проверка. Актуальное значение: " + actual.toString());
        Assert.assertEquals(student.getId(), actual.getId());
        Assert.assertEquals(student.getName(), actual.getName());
        Assert.assertEquals(student.getRating(), actual.getRating());
    }

    @Test
    public void testUpdateAndRead(){
        int id = student.getId();
        Student updStudent = new Student(id, "testPetya", 4);
        base.update(updStudent);
        Student actual = base.get(id);
        System.out.println("Вторая проверка. Проверяемое значение: " + updStudent.toString());
        System.out.println("Вторая проверка. Актуальное значение: " + actual.toString());
        Assert.assertEquals(updStudent.getId(), actual.getId());
        Assert.assertEquals(updStudent.getName(), actual.getName());
        Assert.assertEquals(updStudent.getRating(), actual.getRating());
    }

    @After()
    public void testDelete(){
        base.delete(student.getId());
    }

}
