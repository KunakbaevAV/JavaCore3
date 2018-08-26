import lesson06.MassFromNumbers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @autor Kunakbaev Artem
 */
public class TestMassFromNumbers {
    private MassFromNumbers testMFN;

    @Before
    public void testBefore(){
        testMFN = new MassFromNumbers(1,4);
    }

    @Test
    public void testMFN1(){
        Assert.assertTrue(testMFN.isMassValid(new int[]{1,4}));
    }

    @Test
    public void testMFN2(){
        Assert.assertTrue(testMFN.isMassValid(new int[]{1,4,4,1,1,1,4}));
    }

    @Test
    public void testMFN3(){
        Assert.assertFalse(testMFN.isMassValid(new int[]{1,4,4,1,1,1,4,3}));
    }

    @Test
    public void testMFN4(){
        Assert.assertFalse(testMFN.isMassValid(new int[]{1,2,3,4}));
    }
}
