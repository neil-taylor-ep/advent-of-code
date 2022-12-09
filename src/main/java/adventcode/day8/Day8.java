package adventcode.day8;

import adventcode.day7.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    static final int SIZE = 5;

    public static void main(String[] args) throws IOException {

        int[][] trees = new int[SIZE][SIZE];

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input8test.txt")))) {

            int row = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                int col = 0;
                for (char c : line.toCharArray()) {
                    trees[row][col] = c - 48;
                    col++;
                }
                System.out.println(Arrays.toString(trees[row]));
                row++;
            }
            System.out.println(" ^ input");
            System.out.println("Visible trees: " + findVisible(trees));
        }

    }

    private static int findVisible(final int[][] trees) {
        int count = 0;
        // Looking from left and right
        for (int r = 1; r<98; r++) {
            int[] row = trees[r];
            count += countVisible(row);
            count += countVisible(reverse(row));
        }

        // Looking from top and bottom
        for (int c=1; c<SIZE-1; c++) {
            int[] col = new int[SIZE];
            for (int r=0; r<SIZE; r++) {
                col[r] = trees[r][c];
            }
            count += countVisible(col);
            count += countVisible(reverse(col));
        }

        return count;
    }


    private static int countVisible(int[] line) {
        int count = 0;
        int currentHeight = -1;
        for (int height : line) {
            if (height > currentHeight) {
                count++;
                currentHeight = height;
            }
        }
        System.out.println(Arrays.toString(line) + " has " + count + " visible");
        return count;
    }


    private static int[] reverse(int[] array) {
        int[] result = new int[array.length];

        for (int i=0; i< array.length; i++) {
            result[i] = array[array.length - i - 1];
        }

        return result;
    }
}
