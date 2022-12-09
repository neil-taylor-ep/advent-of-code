package adventcode.day7;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private int weight;
    private List<Node> children = new ArrayList<Node>();
    Node parent;

    public static Node createFile(String[] fileParts, Node parent) {
        return new Node(fileParts, parent);
    }

    public static Node createDir(String name, Node parent) {
        return new Node(name, parent);
    }

    private Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    private Node(String[] fileParts, Node parent) {
        this(fileParts[1], parent);
        weight = Integer.parseInt(fileParts[0]);
        parent.addWeightToParents(weight);
    }

    @Override
    public String toString() {
        return name + " " + weight + (isFile() ? "" : " (dir)");
    }

    private void addWeightToParents(int weight) {
        this.weight += weight;
        if (parent != null) {
            parent.addWeightToParents(weight);
        }
    }

    public void prettyPrint(int offset) {
        for (int i = 0; i<offset; i++) {
            System.out.print(".");
        }
        System.out.print(name);
        if (!isFile()) {
            System.out.print(" (dir)");
        }
        System.out.println(" " + weight);

        for (Node child : children) {
            child.prettyPrint(offset + 2);
        }
    }

    public List<Node> allNodes() {
        List<Node> nodes = new ArrayList<Node>();
        if (!isFile()) {
            nodes.add(this);
        }
        for (Node child : children) {
            if (!child.isFile()) {
                nodes.addAll(child.allNodes());
            }
        }
        return nodes;
    }

    private boolean isFile() {
        return children.isEmpty();
    }

    public int getWeight() {
        return weight;
    }
}
