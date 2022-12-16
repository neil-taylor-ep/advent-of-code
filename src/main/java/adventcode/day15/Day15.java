package adventcode.day15;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Day15 {


    public static void main(String[] args) throws IOException {

        Point[] sensors = new Point[33];
        Point[] beacons = new Point[33];
        Set<Point> occupied = new HashSet<>();

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input15.txt")))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                sensors[index] = new Point(extractTrim(parts, 2), extractTrim(parts, 3));
                beacons[index] = new Point(extractTrim(parts, 8), extract(parts, 9));
                occupied.add(sensors[index]);
                occupied.add(beacons[index]);
                index++;
            }

            int[] sensorRange = new int[sensors.length];
            for (int i=0; i<sensorRange.length; i++) {
                sensorRange[i] = getManhatten(sensors[i], beacons[i]);
            }

            int y = 2000000;
            int startX = -10000000;      //Check the start and end; they should be min and max reach of sensors
            int endX = 10000000;

            int count = 0;
            for (int x=startX; x<endX; x++) {
                for (int i=0; i<sensors.length; i++) {
                    // If the sensor's range reaches point x,y then it's covered and we can increment count and break
                    int distanceToSensor = getManhatten(new Point(x, y), sensors[i]);
                    if (distanceToSensor <= sensorRange[i]) {
                        if (!occupied.contains(new Point(x, y))) {
                            count++;
                            break;
                        }
                    }
                }

            }
            System.out.println();
            System.out.println("Count: " + count);
        }


    }

    private static int getManhatten(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    private static int extractTrim(String[] parts, int i) {
        String part = parts[i].substring(0, parts[i].length()-1);
        return Integer.parseInt(part.split("=")[1]);
    }

    private static int extract(String[] parts, int i) {
        return Integer.parseInt(parts[i].split("=")[1]);
    }

}