package adventcode.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    private static String UP = "..";

    public static void main(String[] args) throws IOException {

        // Part 1
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("input7.txt")))) {

            String line;
            Node pointer = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("$ cd")) {
                    // Change directory
                    String name = line.split(" ")[2];
                    if (UP.equals(name)) {
                        pointer = pointer.parent;
                    } else {
                        Node node = Node.createDir(name, pointer);
                        pointer = node;
                    }
                } else if (line.startsWith("$ ls")) {
                    // List commands do not mutate the tree; Do nothing
                } else if (line.startsWith("dir")) {
                    // Dir commands do not mutate the tree; Do nothing
                } else {
                    // Must be a file
                    String fileParts[] = line.split(" ");
                    Node.createFile(fileParts, pointer);
                }
            }
            // Get back to root node
            while (pointer.parent != null) {
                pointer = pointer.parent;
            }
            pointer.prettyPrint(0);
            totalUnder(pointer, 100000);

            // Part 2
            int limit = 70000000;
            int required = 30000000;
            int used = 40389918;
            int toFree = required + used - limit;
            findSmallest(pointer, toFree);
        }


    }

    private static void totalUnder(Node pointer, int size) {
        List<Node> nodes = pointer.allNodes();
        int total = nodes.stream()
            .filter(n -> n.getWeight() < size)
            .collect(Collectors.summingInt(Node::getWeight))
            .intValue();

        System.out.println("Total size of all dirs under " + size + " is " + total);
    }

    private static void findSmallest(Node pointer, int toFree) {
        int smallest = Integer.MAX_VALUE;
        for (Node node : pointer.allNodes()) {
            if (node.getWeight() > toFree && node.getWeight() < smallest) {
                smallest = node.getWeight();
                System.out.println("Node " + node + " is the smallest.");
            }
        }
    }

}
