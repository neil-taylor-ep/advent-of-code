package adventcode.day14;

import java.awt.*;

public class Cave {

    // Test
    ///private int width = 9;
    ///private int depth = 9;
    ///private int offset = 494;

    // Part 1
    //private int width = 69;
    //private int depth = 170;
    //private int offset = 460;

    // Part 2
    private int width = 469;
    private int depth = 172;
    private int offset = 260;

    private char[][] data;

    public Cave() {
        this.data = new char[width+1][depth+1];
    }

    public void drawLine(Point from, Point to) {
        int dx = to.x - from.x;
        int dy = to.y - from.y;

        int stepX = Integer.signum(dx);
        int stepY = Integer.signum(dy);
        int distance = (int)Math.round(to.distance(from));

        for (int i = 0; i<=distance; i++) {
            int x = stepX * i + from.x - offset;
            int y = from.y + stepY * i;
            data[x][y] = '#';
        }
    }

    public void prettyPrint() {
        for (int y=0; y<=depth; y++) {
            for (int x=0; x<=width; x++) {
                if (data[x][y] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print(data[x][y]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean addSand() {
        Point thisPoint = new Point(500, 0);
        data[500-offset][0] = 'o';
        Point nextPoint = getNextPoint(thisPoint);
        while (nextPoint != null && !thisPoint.equals(nextPoint)) {
            data[thisPoint.x-offset][thisPoint.y] = 0;
            thisPoint = nextPoint;
            nextPoint = getNextPoint(thisPoint);
            if (nextPoint != null) {
                data[thisPoint.x-offset][thisPoint.y] = 'o';
            }
        }
        return nextPoint == null || nextPoint.y == 0;
    }


    /**
     * Return next point the sand can move to. If the sand can't move then the returned point
     * will be the same as the current point. If the sand falls out of the grid, then null is
     * returned.
     * @param point
     * @return
     */
    private Point getNextPoint(Point point) {
        if (point.y == depth) {
            // At bottom
            return null;
        } else if (data[point.x-offset][point.y + 1] == 0) {
            // Can move down
            return new Point(point.x, point.y + 1);
        } else if (point.x == offset) {
            //Fall off left side
            return null;
        } else if (data[point.x - 1 - offset][point.y + 1] == 0) {
            // Can move left and down
            return new Point(point.x - 1, point.y + 1);
        } else if (point.x == width + offset) {
            //Fall off right
            return null;
        } else if (data[point.x + 1 - offset][point.y + 1] == 0) {
            //Can move right and down
            return new Point(point.x + 1, point.y + 1);
        } else {
            // Can't move
            return point;
        }
    }

    public int countGrains() {
        int grains = 0;
        for (int x=0; x<=width; x++) {
            for (int y=0; y<=depth; y++) {
                if (data[x][y] == 'o') {
                    grains++;
                }
            }
        }
        return grains;
    }

    public void addInfiniteFloor() {
        for (char[] column: data) {
            column[depth] = '#';
        }
    }
}
