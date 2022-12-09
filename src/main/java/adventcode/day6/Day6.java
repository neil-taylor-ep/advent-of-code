package adventcode.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) throws IOException {

        Deque<Character> buffer = new ArrayDeque<>();

        // Part 1
        try (InputStreamReader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("input6.txt"))) {
            int offset = 4;
            buffer.push((char) reader.read());
            buffer.push((char) reader.read());
            buffer.push((char) reader.read());
            buffer.push((char) reader.read());
            while(!isStartMarker(buffer)) {
                offset++;
                buffer.push((char) reader.read());
                buffer.pollLast();
            }
            System.out.println(buffer + " at " + offset);
        }

        buffer = new ArrayDeque<>();
        // Part 2
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input6.txt")))) {
            int offset = 14;
            for (int i=0; i<14; i++) {
                buffer.push((char) reader.read());
            }
            while(!isMessageMarker(buffer)) {
                offset++;
                buffer.push((char) reader.read());
                buffer.pollLast();
            }
            System.out.println(buffer + " at " + offset);
        }
    }

    private static boolean isStartMarker(Deque<Character> buffer) {
        Set<Character> set = buffer.stream().collect(Collectors.toSet());
        return set.size() == 4;
    }

    private static boolean isMessageMarker(Deque<Character> buffer) {
        Set<Character> set = buffer.stream().collect(Collectors.toSet());
        return set.size() == 14;
    }
}
