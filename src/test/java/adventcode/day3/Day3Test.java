package adventcode.day3;

import org.junit.Assert;
import org.junit.Test;

public class Day3Test {

    private Day3 day3 = new Day3();

    @Test
    public void testGetDuplicate() {
        Assert.assertEquals(day3.getPriority("vJrwpWtwJgWrhcsFMMfFFhFp"), 16);
        Assert.assertEquals(day3.getPriority("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"), 38);
        Assert.assertEquals(day3.getPriority("PmmdzqPrVvPwwTWBwg"), 42);
        Assert.assertEquals(day3.getPriority("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"), 22);
        Assert.assertEquals(day3.getPriority("ttgJtRGJQctTZtZT"), 20);
        Assert.assertEquals(day3.getPriority("CrZsJsPPZsGzwwsLwLmpwMDw"), 19);
    }
}
