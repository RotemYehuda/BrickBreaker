// Rotem Yehuda 313223968

package geometry;

/**
 * public class geometry.Point.
 *
 * @author Rotem Yehuda 313223968
 * This class represents a point
 */
public class Point {
    private double x;
    private double y;

    static final double EPSILON = Math.pow(10, -15);

    /**
     * Constructor.
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculates the distance between this point to the other point.
     *
     * @param other The other point
     * @return the distance between this point to the other point
     */
    public double distance(Point other) {
        if (this.equals(other)) {
            return 0;
        }
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * This method checking if two points are equal.
     *
     * @param other The other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this == null || other == null) {
            return false;
        }
        return ((Math.abs(this.getX() - other.getX()) < EPSILON)
                && (Math.abs(this.getY() - other.getY()) < EPSILON));
    }

    /**
     * This method returns the x value of this point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return (int) this.x;
    }

    /**
     * This method returns the y value of this point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return (int) this.y;
    }
}