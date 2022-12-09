package adventcode.day8;

import adventcode.day7.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    static final int SIZE = 99;
    static int[][] trees = new int[SIZE][SIZE];

    // Using a separate array to flag visibility avoids the main problem, which
    // is double counting trees that can be seen from more than one direction.
    static boolean[][] visible = new boolean[SIZE][SIZE];

    public static void main(String[] args) throws IOException {



        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input8.txt")))) {

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
            flagVisible();
            System.out.println(" ^ visible");
            System.out.println("Visible trees: " + countVisible());
        }


        // Part 2
        int maxScore = 0;
        for (int r = 0; r<SIZE; r++) {
            for (int c = 0; c<SIZE; c++) {
                int score = getScore(r, c);
                if (score > maxScore) {
                    maxScore = score;
                }
            }
        }
        System.out.println("Best score is " + maxScore);

    }

    private static void flagVisible() {
        int maxHeight;
        // Examine by row
        for (int r = 0; r < SIZE; r++) {
            // Start from left
            maxHeight = -1;
            for (int c = 0; c < SIZE; c++) {
                if (trees[r][c] > maxHeight) {
                    maxHeight = trees[r][c];
                    visible[r][c] = true;
                }
                if (maxHeight == 9) break; //No point in looking further
            }

            // Now from right
            maxHeight = -1;
            for (int c = SIZE-1; c >= 0; c--) {
                if (trees[r][c] > maxHeight) {
                    maxHeight = trees[r][c];
                    visible[r][c] = true;
                }
                if (maxHeight == 9) break; //No point in looking further
            }
        }

        // Examine by column
        for (int c = 0; c < SIZE; c++) {
            // Start from top
            maxHeight = -1;
            for (int r = 0; r < SIZE; r++) {
                if (trees[r][c] > maxHeight) {
                    maxHeight = trees[r][c];
                    visible[r][c] = true;
                }
                if (maxHeight == 9) break; //No point in looking further
            }

            // Now from bottom
            maxHeight = -1;
            for (int r = SIZE - 1; r>= 0; r--) {
                if (trees[r][c] > maxHeight) {
                    maxHeight = trees[r][c];
                    visible[r][c] = true;
                }
                if (maxHeight == 9) break; //No point in looking further
            }
        }

        // Print it
        for (int r = 0; r<SIZE; r++) {
            System.out.print("[");
            for (int c = 0; c<SIZE; c++) {
                if (visible[r][c]) {
                    System.out.print(trees[r][c]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("]");
        }
    }


    private static int countVisible() {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (visible[row][col]) {
                    count++;
                }
            }
        }
        return count;
    }


    private static int getScore(int r, int c) {
        int score = 0;
        int runningTotal = 1;
        int thisHeight = trees[r][c];

        // Check to the left
        for (int row = r - 1; row>=0; row--) {
            score++;
            if (trees[row][c] >= thisHeight) break;
        }
        runningTotal *= score;

        // Check to the right
        score = 0;
        for (int row = r + 1; row<SIZE; row++) {
            score++;
            if (trees[row][c] >= thisHeight) break;
        }
        runningTotal *= score;

        // Check up
        score = 0;
        for (int col = c - 1; col>=0; col--) {
            score++;
            if (trees[r][col] >= thisHeight) break;
        }
        runningTotal *= score;

        // Check down
        score = 0;
        for (int col = c + 1; col<SIZE; col++) {
            score++;
            if (trees[r][col] >= thisHeight) break;
        }
        runningTotal *= score;

        return runningTotal;
    }
}
