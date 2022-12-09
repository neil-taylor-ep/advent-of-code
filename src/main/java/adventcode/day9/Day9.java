package adventcode.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day9 {

    public static void main(String[] args) throws IOException {

        // Part 1
        Rope rope = new Rope(2);
        moveTheRope(rope);
        System.out.println("Number of tail positions " + rope.positions.size());

        // Part 2
        rope = new Rope(10);
        moveTheRope(rope);
        System.out.println("Number of tail positions " + rope.positions.size());

    }

    private static void moveTheRope(Rope rope) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input9.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String direction = line.split(" ")[0];
                int distance = Integer.parseInt(line.split(" ")[1]);
                switch (direction) {
                    case ("L") :
                        rope.move(-distance, 0);
                        break;
                    case("R") :
                        rope.move(distance, 0);
                        break;
                    case("U") :
                        rope.move(0, distance);
                        break;
                    case("D") :
                        rope.move(0, -distance);
                }
                System.out.println(rope);
            }
        }
    }

}
