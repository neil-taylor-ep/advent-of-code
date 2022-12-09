package adventcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1 {

    public static void main (String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input1.txt")))) {
            int max = 0;
            int running = 0;
            String line;

            int elfCount = 1;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (running > max) {
                        max = running;
                    }
                    System.out.println("Elf " + elfCount + " has " + running);
                    elfCount++;
                    running = 0;
                } else {
                    running += Integer.parseInt(line);
                }
            }
            System.out.println("Highest running total is " + max);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
