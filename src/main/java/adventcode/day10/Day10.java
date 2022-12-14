package adventcode.day10;

import adventcode.day9.Rope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day10 {

    static int cycle = 0;
    static int value = 1;
    static int totalSignalStrength = 0;
    static List<Integer> qualifyingCycles = Arrays.asList(20, 60, 100, 140, 180, 220);

    public static void main(String[] args) throws IOException {

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input10.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("noop")) {
                    draw();
                    cycle++;
                    checkTotal();
                } else {
                    draw();
                    cycle++;
                    checkTotal();

                    draw();
                    cycle++;
                    checkTotal();
                    value += Integer.parseInt(line.split(" ")[1]);
                }
            }
            System.out.println("Total " + totalSignalStrength);
        }
    }

    private static void checkTotal() {
        if (qualifyingCycles.contains(cycle)) {
            totalSignalStrength += cycle*value;
        }
    }

    private static void draw() {
        int pixel = cycle % 40;
        if (Math.abs(value - pixel) < 2) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if (pixel == 39) {
            System.out.println();
        }
    }

}
