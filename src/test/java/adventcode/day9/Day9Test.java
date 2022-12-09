package adventcode.day9;

import org.junit.Assert;
import org.junit.Test;

public class Day9Test {

    private Rope rope2 = new Rope(2);

    @Test
    public void testLeftMovement() {
        //rope2.getTail().setLocation(0,0);
        rope2.move(-1, 0);
        Assert.assertEquals(0, rope2.getTail().x);
        Assert.assertEquals(0, rope2.getTail().y);
    }

    @Test
    public void testDiagonalMovement() {
        rope2.getHead().setLocation(1,1);
        //rope2.getTail().setLocation(0,0);
        rope2.move(0, 1);
        Assert.assertEquals(1, rope2.getTail().x);
        Assert.assertEquals(1, rope2.getTail().y);
    }
}
