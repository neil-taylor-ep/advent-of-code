package adventcode.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main (String[] args) throws IOException {

        Map<String, Integer> results = new HashMap<String, Integer>();
        results.put("A X", 3 + 1);
        results.put("A Y", 6 + 2);
        results.put("A Z", 0 + 3);
        results.put("B X", 0 + 1);
        results.put("B Y", 3 + 2);
        results.put("B Z", 6 + 3);
        results.put("C X", 6 + 1);
        results.put("C Y", 0 + 2);
        results.put("C Z", 3 + 3);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input2.txt")))) {

            int score = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                score += results.get(line);
            }
            System.out.println(score);
        }
    }
}
