package adventcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day1b {

    public static void main (String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input1.txt")))) {
            TreeSet<Integer> counts = new TreeSet<>();
            int running = 0;
            String line = reader.readLine();
            boolean eof = false;

            while (!eof) {
                if (line == null) {
                    eof = true;
                }

                if (eof || line.isEmpty()) {
                    counts.add(running);
                    running = 0;
                    if (counts.size() > 3) {
                        counts.pollFirst();
                    }
                    System.out.println(counts);
                } else {
                    running += Integer.parseInt(line);
                }
                line = reader.readLine();

            }
            int top3 = counts.stream().collect(Collectors.summingInt(Integer::intValue));
            System.out.println("Top 3 elfs have  " + top3);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
