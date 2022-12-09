package adventcode.day4;

import org.junit.Assert;
import org.junit.Test;

public class Day4Test {

    private Day4 d = new Day4();

    @Test
    public void testHasCompleteOverlap() {
        Assert.assertFalse(d.hasCompleteOverlap("2-4,6-8"));
        Assert.assertFalse(d.hasCompleteOverlap("2-3,4-5"));
        Assert.assertFalse(d.hasCompleteOverlap("5-7,7-9"));
        Assert.assertTrue(d.hasCompleteOverlap("2-8,3-7"));
        Assert.assertTrue(d.hasCompleteOverlap("6-6,4-6"));
        Assert.assertFalse(d.hasCompleteOverlap("2-6,4-8"));
    }

    @Test
    public void testHasPartialOverlap() {
        Assert.assertFalse(d.hasPartialOverlap("2-4,6-8"));
        Assert.assertFalse(d.hasPartialOverlap("2-3,4-5"));
        Assert.assertTrue(d.hasPartialOverlap("5-7,7-9"));
        Assert.assertTrue(d.hasPartialOverlap("2-8,3-7"));
        Assert.assertTrue(d.hasPartialOverlap("6-6,4-6"));
        Assert.assertTrue(d.hasPartialOverlap("2-6,4-8"));
    }
}
