package adventcode.day9;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Rope {
    Set<String> positions = new HashSet<>();

    Point[] segments;

    public Rope(int length) {
        segments = new Point[length];
        for (int i=0; i<length; i++) {
            segments[i] = new Point();
        }
        positions.add(getTail().x + "," + getTail().y);
    }

    public Point getTail() {
        return segments[segments.length - 1];
    }

    public Point getHead() {
        return segments[0];
    }

    public void move(int dx, int dy) {
        int distance = Math.max(Math.abs(dx), Math.abs(dy));
        for (int i=0; i<distance; i++) {
            getHead().translate(Integer.signum(dx), Integer.signum(dy));
            for (int s = 1; s<segments.length; s++) {
                follow(segments[s], segments[s-1]);
            }
            positions.add(getTail().x + "," + getTail().y);
        }
    }

    private void follow(Point follower, Point leader) {
        // Translate the follower to catch up with the leader.

        int dx = Math.abs(leader.x - follower.x);
        int dy = Math.abs(leader.y - follower.y);
        // First check if they are touching
        if (dx <= 1 && dy <= 1) {
            return;
        }

        if (leader.x == follower.x && leader.y - follower.y == 2) {
            follower.translate(0, 1);       // Up
        } else if (leader.x == follower.x && leader.y - follower.y == -2) {
            follower.translate(0, -1);      // Down
        } else if (leader.x - follower.x == -2 && leader.y == follower.y) {
            follower.translate(-1, 0);      // Left
        } else if (leader.x - follower.x == 2 && leader.y == follower.y) {
            follower.translate(1, 0);       // Right
        } else if (leader.x < follower.x && leader.y > follower.y) {
            follower.translate(-1, 1);      // Left and up
        } else if (leader.x > follower.x && leader.y > follower.y) {
            follower.translate(1, 1);       // Right and up
        } else if (leader.x > follower.x && leader.y < follower.y) {
            follower.translate(1, -1);      // Right and down
        } else if (leader.x < follower.x && leader.y < follower.y) {
            follower.translate(-1, -1);     // Left and down
        } else {
            throw new RuntimeException("Unexpected positions l=" + leader + " f=" + follower);
        }
    }


    @Override
    public String toString() {
        return "h[" + getHead().x + "," + getHead().y + "] t[" + getTail().x + "," + getTail().y + "]";
    }

}
