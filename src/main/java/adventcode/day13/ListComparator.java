package adventcode.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListComparator implements Comparator<List> {

    @Override
    public int compare(List left, List right) {
        Iterator leftIterator = left.iterator();
        Iterator rightIterator = right.iterator();

        while (true) {
            if (leftIterator.hasNext() && rightIterator.hasNext()) {

                Object leftItem = leftIterator.next();
                Object rightItem = rightIterator.next();

                if (!leftItem.getClass().equals(rightItem.getClass())) {
                    // Mixed types
                    if (isInt(leftItem)) {
                        int value = getInt(leftItem);
                        leftItem = new ArrayList();
                        ((List) leftItem).add(value);
                    } else {
                        // Right must be an int
                        int value = getInt(rightItem);
                        rightItem = new ArrayList();
                        ((List) rightItem).add(value);
                    }
                }

                if (isInt(leftItem) && isInt(rightItem)) {
                    // Both ints
                    int result = ((Integer) leftItem).compareTo((Integer) rightItem);
                    if (result != 0) {
                        return result;
                    }
                } else if (isList(leftItem) && isList(rightItem)) {
                    // Both lists
                    int result = compare((List) leftItem, (List) rightItem);
                    if (result != 0) {
                        return result;
                    }
                }
            } else {
                // One has run out
                if (!leftIterator.hasNext() && !rightIterator.hasNext()) {
                    return 0;
                } else if (!leftIterator.hasNext()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    private int getInt(Object item) {
        return ((Integer)item).intValue();
    }

    private boolean isList(Object item) {
        return item instanceof List;
    }

    private boolean isInt(Object item) {
        return item instanceof Integer;
    }
}
