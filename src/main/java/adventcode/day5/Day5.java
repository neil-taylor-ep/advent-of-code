package adventcode.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Day5 {

    private Stack[] stacks;

    public Day5() {
        stacks = new Stack[10];
        for (int i=1; i<10; i++) {
            stacks[i] = new Stack();
        }
        pushOntoStack(stacks[1], "BWN");
        pushOntoStack(stacks[2], "LZSPTDMB");
        pushOntoStack(stacks[3], "QHZWR");
        pushOntoStack(stacks[4], "WDVJZR");
        pushOntoStack(stacks[5], "SHMB");
        pushOntoStack(stacks[6], "LGNJHVPB");
        pushOntoStack(stacks[7], "JQZFHDLS");
        pushOntoStack(stacks[8], "WSFJGQB");
        pushOntoStack(stacks[9], "ZWMSCDJ");
    }

    private void pushOntoStack(Stack stack, String s) {
        for (Character c : s.toCharArray()) {
            stack.push(c);
        }
    }

    public static void main(String[] args) throws IOException {

        Day5 d = new Day5();

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input5.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int qty = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);
                d.move(qty, from, to);
            }
            d.heads();
        }

        // Part 2
        d = new Day5();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input5.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int qty = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);
                d.moveStack(qty, from, to);
            }
            d.heads();
        }
    }

    private void heads() {
        for (int i=1; i<10; i++) {
            System.out.print(stacks[i].peek());
        }
        System.out.println();
    }

    private void move(int qty, int from, int to) {
        for (int i = 0; i<qty; i++) {
            char c = (char) stacks[from].pop();
            stacks[to].push(c);
        }
    }

    private void moveStack(int qty, int from, int to) {
        Deque<Character> tmp = new ArrayDeque<>();
        for (int i = 0; i<qty; i++) {
            char c = (char) stacks[from].pop();
            tmp.push(c);
        }
        while (!tmp.isEmpty()) {
            char c = tmp.poll();
            stacks[to].push(c);
        }
    }


}
