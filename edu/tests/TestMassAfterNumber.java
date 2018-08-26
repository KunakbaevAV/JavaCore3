import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lesson06.*;

/**
 * @autor Kunakbaev Artem
 */
public class TestMassAfterNumber {
    private MassAfterNumber testMAN;

    @Before
    public void testBefore(){
        testMAN = new MassAfterNumber(4);
    }

    @Test
    public void testFindMAN1(){
        Assert.assertArrayEquals(new int[]{5,6}, testMAN.findMassAfterNumber(new int[]{2,3,4,5,6}));
    }

    @Test
    public void testFindMAN2(){
        Assert.assertArrayEquals(new int[]{5,6,7}, testMAN.findMassAfterNumber(new int[]{2,3,4,5,6,4,5,6,7}));
    }

    @Test(expected = RuntimeException.class)
    public void testFindMAN3(){
        Assert.assertArrayEquals(new int[0], testMAN.findMassAfterNumber(new int[]{2,3,5,6}));
    }
}

