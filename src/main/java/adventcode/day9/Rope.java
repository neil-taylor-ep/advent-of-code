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

        int dx = Math.abs(leader.x - follower.x);
        int dy = Math.abs(leader.y - follower.y);

        // If they are not touching
        if (dx > 1 || dy > 1) {
            // Translate the follower to catch up with the leader.
            follower.translate(Integer.signum(leader.x - follower.x), Integer.signum(leader.y - follower.y));
        }
        
    }


    @Override
    public String toString() {
        return "h[" + getHead().x + "," + getHead().y + "] t[" + getTail().x + "," + getTail().y + "]";
    }

}
