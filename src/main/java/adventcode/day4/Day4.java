package adventcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day4 {

    public static void main(String[] args) throws IOException {

        Day4 d = new Day4();

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input4.txt")))) {

            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (d.hasCompleteOverlap(line)) {
                    count++;
                }
            }
            System.out.println(count);
        }

        // Part 2
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input4.txt")))) {

            long start = System.currentTimeMillis();
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (d.hasPartialOverlap(line)) {
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(count + "(" + (end-start) + "ms)");
        }
    }

    public boolean hasCompleteOverlap(final String line) {
        String[] parts = line.split(",");
        String[] range1 = parts[0].split("-");
        String[] range2 = parts[1].split("-");

        return (first(range1) <= first(range2) && last(range1) >= last(range2))
            || (first(range1) >= first(range2) && last(range1) <= last(range2));
    }

    public boolean hasPartialOverlap(final String line) {
        String[] parts = line.split(",");
        String[] range1 = parts[0].split("-");
        String[] range2 = parts[1].split("-");

        return (first(range1) >= first(range2) && first(range1) <= last(range2))    // start of range 1 lies in range 2
                || (last(range1) >= first(range2) && last(range1) <= last(range2))  // end of range 1 lies in range 2
                || (first(range2) >= first(range1) && first(range2) <= last(range1))// start of range 2 lies in range 1
                || (last(range2) >= first(range1) && last(range2) <= last(range1)); // start of range 2 lies in range 1

        /* more efficient? instead of checking for overlap, checks for gap
        if (first(range1) > last(range2) || last(range1) < first(range2)) {
            return false;
        } else {
            return true;
        }
         */
    }


    private int first(String[] range) {
        return Integer.parseInt(range[0]);
    }
    private int last(String[] range) {
        return Integer.parseInt(range[1]);
    }


}
