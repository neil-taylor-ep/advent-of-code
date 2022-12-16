package adventcode.day14;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day14 {


    public static void main(String[] args) throws IOException {

        // Part 1
        Cave cave = readCave();
        cave.addInfiniteFloor();        // Part 2
        boolean overflow = false;
        while (!overflow) {
            overflow = cave.addSand();
        }
        cave.prettyPrint();
        System.out.println("Total grains: " + cave.countGrains());

    }

    private static Cave readCave() throws IOException {
        Cave cave = new Cave();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input14.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] path = line.split(" -> ");
                Point lastPoint = null;
                for (String point : path) {
                    String[] xy = point.split(",");
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);

                    Point thisPoint = new Point(x, y);
                    if (lastPoint != null) {
                        cave.drawLine(lastPoint, thisPoint);
                    }
                    lastPoint = thisPoint;
                }
            }
        }

        return cave;
    }

}