package adventcode.day12;

import adventcode.day11.InspectResult;
import adventcode.day11.Monkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day12 {

    static Map<String, Character> vertices = new HashMap<>();
    static Map<String, Integer> dist = new HashMap<>();
    static Map<String, String> prev = new HashMap<>();
    static Set<String> Q = new HashSet<>();
    static String start = null;
    static String end = null;

    public static void main(String[] args) throws IOException {

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input12.txt")))) {

            // Read the input into a map of coordinate -> altitude
            int y= 0;
            String line;
            while ((line = reader.readLine()) != null) {
                int x = 0;
                for (char c : line.toCharArray()) {
                    String key = x + "," + y;
                    if (c == 83) {
                        start = key;
                        vertices.put(key, 'a');
                    } else if (c == 69) {
                        end = key;
                        vertices.put(key, 'z');
                    } else {
                        vertices.put(key, c);
                    }
                    dist.put(key, Integer.MAX_VALUE);
                    Q.add(key);
                    x++;
                }
                y++;
            }

            // Dijkstra
            dist.put(start, 0);
            while (!Q.isEmpty()) {
                String u = getNearest();
                if (end.equals(u)) {
                    break;
                }
                Q.remove(u);

                for (String v : getNeighbours(u)) {
                    int alt = dist.get(u) + 1;
                    if (alt < dist.get(v)) {
                        dist.put(v, alt);
                        prev.put(v, u);
                    }
                }
            }

            // Backtrack from end to start to find the path
            List<String> path = new ArrayList<>();
            String u = end;
            while (prev.get(u) != null) {
                path.add(u);
                u = prev.get(u);
            }

            System.out.println("Path " + path.size());

        }



    }

    private static String getNearest() {
        String nearest = null;
        int min = Integer.MAX_VALUE;
        for (String v : Q) {
            int d = dist.get(v);
            if (d < min) {
                min = dist.get(v);
                nearest = v;
            }
        }
        return nearest;
    }


    private static Set<String> getNeighbours(String u) {
        int x = Integer.parseInt(u.split(",")[0]);
        int y = Integer.parseInt(u.split(",")[1]);

        Set<String> neighbours = new HashSet<>();
        if (isPassable(u, x-1 + "," + y)) {
            neighbours.add(x-1 + "," + y);
        }
        if (isPassable(u, x+1 + "," + y)) {
            neighbours.add(x+1 + "," + y);
        }
        if (isPassable(u, x + "," + (y-1))) {
            neighbours.add(x + "," + (y-1));
        }
        if (isPassable(u, x + "," + (y+1))) {
            neighbours.add(x + "," + (y+1));
        }
        return neighbours;
    }

    private static boolean isPassable(String from, String to) {
        if (vertices.containsKey(to)) {
            int distanceToClimb = vertices.get(to) - vertices.get(from);
            return distanceToClimb < 2;
        }
        return false;
    }
}
