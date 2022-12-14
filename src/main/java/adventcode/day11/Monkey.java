package adventcode.day11;

import java.util.ArrayList;
import java.util.List;

public abstract class Monkey implements Comparable<Monkey> {

    private List<Long> items;

    private int divisor;
    private int trueTarget;
    private int falseTarget;
    private int inspectCount;

    public Monkey(int[] items, int divisor, int trueTarget, int falseTarget) {
        this.items = new ArrayList<>();
        for (int i : items) {
            this.items.add(Long.valueOf(i));
        }
        this.divisor = divisor;
        this.trueTarget = trueTarget;
        this.falseTarget = falseTarget;
    }

    abstract long operation(long number);

    public InspectResult inspect(long gcm) {
        inspectCount++;
        long firstItem = items.remove(0);
        //long newItem = operation(firstItem) / 3;
        long newItem = operation(firstItem) % gcm;
        if (newItem % divisor == 0) {
            return new InspectResult(newItem, trueTarget);
        } else {
            return new InspectResult(newItem, falseTarget);
        }
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public void give (long item) {
        items.add(item);
    }

    public String toString() {
        return "Inspected " + inspectCount;
    }

    public int getDivisor() {
        return divisor;
    }

    @Override
    public int compareTo(Monkey m) {
        return m.inspectCount - inspectCount;
    }

    public long getInspectCount() {
        return inspectCount;
    }
}
