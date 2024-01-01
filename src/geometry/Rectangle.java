// Rotem Yehuda 313223968

package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * public class geometry.Rectangle.
 *
 * @author Rotem Yehuda
 * This class represents a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line upperEdge;
    private Line lowerEdge;
    private Line rightEdge;
    private Line leftEdge;

    /**
     * Constructor- from point, width and height.
     *
     * @param upperLeft the upper left point
     *                  of the rectangle
     * @param width     the rectangle's width
     * @param height    the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.setEdges();
    }

    /**
     * Constructor- from location coordinates, width and height.
     *
     * @param x      the x coordinate of the upper left point
     * @param y      the y coordinate of the upper left point
     * @param width  the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.setEdges();
    }

    /**
     * This method sets the edges line equation.
     */
    public void setEdges() {
        this.upperEdge = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY());
        this.leftEdge = new Line(upperLeft.getX(), upperLeft.getY() + this.height,
                upperLeft.getX(), upperLeft.getY());
        this.lowerEdge = new Line(upperLeft.getX(), upperLeft.getY() + this.height,
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        this.rightEdge = new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
    }

    /**
     * This method returns the rectangle's width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * This method returns the rectangle's height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * This method returns the rectangle's location.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * This method returns the line that defines the upper edge.
     *
     * @return the line that defines the upper edge
     */
    public Line getUpperEdge() {
        return this.upperEdge;
    }

    /**
     * This method returns the line that defines the lower edge.
     *
     * @return the line that defines the lower edge
     */
    public Line getLowerEdge() {
        return this.lowerEdge;
    }

    /**
     * This method returns the line that defines the left edge.
     *
     * @return the line that defines the left edge
     */
    public Line getLeftEdge() {
        return this.leftEdge;
    }

    /**
     * This method returns the line that defines the right edge.
     *
     * @return the line that defines the right edge
     */
    public Line getRightEdge() {
        return this.rightEdge;
    }

    /**
     * This method returns list of intersection points.
     *
     * @param line the specified line.
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> interPoints = new ArrayList<Point>();

        Point intersectionPoint = line.intersectionWith(upperEdge);
        if (intersectionPoint != null) {
            interPoints.add(intersectionPoint);
        }

        intersectionPoint = line.intersectionWith(leftEdge);
        if (intersectionPoint != null) {
            interPoints.add(intersectionPoint);
        }

        intersectionPoint = line.intersectionWith(rightEdge);
        if (intersectionPoint != null) {
            interPoints.add(intersectionPoint);
        }

        intersectionPoint = line.intersectionWith(lowerEdge);
        if (intersectionPoint != null) {
            interPoints.add(intersectionPoint);
        }

        return interPoints;
    }

    /**
     * This method re-locate the rectangle.
     *
     * @param newUpperLeft the new rectangle's upper left point.
     */
    public void moveRect(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
        this.setEdges();
    }
}