package adventcode.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Day2b {

    public static void main (String[] args) throws IOException {

        Map<String, Integer> results = new HashMap<String, Integer>();
        results.put("A X", 3);   // rock, lose, scissors
        results.put("A Y", 4);   // rock, draw, rock
        results.put("A Z", 8);   // rock, win, paper
        results.put("B X", 1);   // paper, lose, rock
        results.put("B Y", 5);   // paper, draw, paper
        results.put("B Z", 9);   // paper, win, scissors
        results.put("C X", 2);   // scissors, lose, paper
        results.put("C Y", 6);   // scissors, draw, scissors
        results.put("C Z", 7);   // scissors, win, rock

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
