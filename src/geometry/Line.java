// Rotem Yehuda 313223968

package geometry;

import java.util.List;

/**
 * public class geometryPrimitives.geometry.Line.
 *
 * @author Rotem Yehuda
 * This class represents a line
 */
public class Line {
    private Point start;
    private Point end;
    private Point minPoint;
    private Point maxPoint;

    // Equation's coefficients
    private double a;
    private double b;
    private double c;

    /**
     * Constructor- from two points.
     *
     * @param start The start point of the line
     * @param end   The end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.setMinMaxPoint();
        this.setCoefficients();
    }

    /**
     * Constructor- from coordinates of two points.
     *
     * @param x1 The x coordinate of the start point of the line
     * @param y1 The y coordinate of the start point of the line
     * @param x2 The x coordinate of the end point of the line
     * @param y2 The y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.setMinMaxPoint();
        this.setCoefficients();
    }

    /**
     * this method calculates and sets the line equation coefficients.
     */
    public void setCoefficients() {
        this.a = this.end().getY() - this.start().getY();
        this.b = this.start().getX() - this.end().getX();
        this.c = this.a * (this.start().getX()) + this.b * (this.start().getY());
    }

    /**
     * this method set the minimum and maximum points based on x values.
     */
    public void setMinMaxPoint() {
        if (this.start.getX() <= this.end.getX()) {
            this.minPoint = this.start;
            this.maxPoint = this.end;
        } else {
            this.minPoint = this.end;
            this.maxPoint = this.start;
        }
    }

    /**
     * This method checks if certain point satisfies the equation
     * of this line (if this line contains the point).
     *
     * @param p the checked point.
     * @return true if it satisfies the equation, false otherwise.
     */
    public boolean satisfiesTheEquation(Point p) {
        return this.a * p.getX() + this.b * p.getY() + this.c == 0;
    }

    /**
     * This method returns the start point of this line.
     *
     * @return the first point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method returns the end point of this line.
     *
     * @return the second point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method calculate the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This method calculate the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * This method checking if two lines define by the same points.
     *
     * @param other Another line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.minPoint.equals(other.minPoint)
                && this.maxPoint.equals(other.maxPoint);
    }

    /**
     * This method checks if the lines have common x values.
     *
     * @param other The other line.
     * @return 2- if theres more than one, 1-if there only one, 0-otherwise.
     */
    public int commonXRange(Line other) {
        double exp1 = Math.max(this.maxPoint.getX(), other.maxPoint.getX())
                - Math.min(this.minPoint.getX(), other.minPoint.getX());
        double exp2 = (this.maxPoint.getX() - this.minPoint.getX())
                + (other.maxPoint.getX() - other.minPoint.getX());
        // in case there is more than one common x value
        if (exp1 < exp2) {
            return 2;
        } else if (exp1 == exp2) {
            // in case there is only one common x value
            return 1;
        }
        //in case the lines don't have common x values
        return 0;
    }

    /**
     * This method checks if the lines have common y values.
     *
     * @param other The other line.
     * @return 2- if theres more than one, 1-if there only one, 0-otherwise.
     */
    public int commonYRange(Line other) {
        double min1 = Math.min(this.start.getY(), this.end.getY());
        double max1 = Math.max(this.start.getY(), this.end.getY());
        double min2 = Math.min(other.start.getY(), other.end.getY());
        double max2 = Math.max(other.start.getY(), other.end.getY());
        double exp1 = Math.max(max1, max2) - Math.min(min1, min2);
        double exp2 = (max1 - min1) + (max2 - min2);
        // in case there is more than one common y value
        if (exp1 < exp2) {
            return 2;
        } else if (exp1 == exp2) {
            // in case there is only one common y value
            return 1;
        }
        //in case the lines don't have common y values
        return 0;
    }

    /**
     * This method checking if this line contain certain point.
     *
     * @param p The point
     * @return true if the line contains the point, false otherwise
     */
    public boolean containThePoint(Point p) {
        boolean xRange = (p.getX() >= this.minPoint.getX())
                && (p.getX() <= this.maxPoint.getX());
        boolean yRange = (p.getY() >= Math.min(this.start.getY(), this.end.getY()))
                && (p.getY() <= Math.max(this.start.getY(), this.end.getY()));
        return xRange && yRange;
    }

    /**
     * This method checks if there is intersection point
     * when at least one of the lines is a point.
     *
     * @param other The other line
     * @return the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point atLeastOneLineIsAPoint(Line other) {
        // if both lines are points
        if (this.length() == 0 && other.length() == 0) {
            return this.start;
        }
        // If only this line is a point and it satisfies the other line equation
        if (this.length() == 0
                && other.satisfiesTheEquation(this.start)) {
            return this.start;
        }
        //if the other line is a point and it satisfies this line equation
        if (other.length() == 0
                && this.satisfiesTheEquation(other.start)) {
            return other.start;
        }
        return null;
    }

    /**
     * This method checks if there is intersection point
     * when both of the lines are parallel to Y-axis.
     *
     * @param other The other line.
     * @return the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point bothLinesAreParallel(Line other) {
        //there is only one common point
        if (this.commonYRange(other) == 1) {
            if (this.start.equals(other.start)
                    || this.start.equals(other.end)) {
                return this.start;
            }
            return this.end;
        }
        //if they have more than one or no common points
        return null;
    }

    /**
     * This method checks if there is intersection point
     * when the line got the same slope.
     *
     * @param other The other line.
     * @return the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point sameSlope(Line other) {
        //if the lines got the same y intercept point and common x values
        if (this.c / this.b == other.c / other.b
                && this.commonXRange(other) == 1) {
            if (this.minPoint.equals(other.maxPoint)) {
                return this.minPoint;
            }
            return other.maxPoint;
        }
        return null;
    }

    /**
     * This method checks if there is intersection point
     * when the line got different slope.
     *
     * @param other The other line.
     * @return the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point differentSlope(Line other) {
        double determinant = this.a * other.b - other.a * this.b;

        //calculate the coordinates of the interception point
        double x = (other.b * this.c - this.b * other.c) / determinant;
        double y = (this.a * other.c - other.a * this.c) / determinant;
        Point interPoint = new Point(x, y);
        // if both lines contain the point
        if (this.containThePoint(interPoint)
                && other.containThePoint(interPoint)) {
            return interPoint;
        }
        return null;
    }

    /**
     * This method check if the lines are intersecting.
     *
     * @param other The other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        boolean commonRanges = this.commonXRange(other) >= 1 && this.commonYRange(other) >= 1;
        // if they don't have common definition range
        if (!commonRanges) {
            return false;
        }
        // if at least one of the lines is a point
        if (this.length() == 0 || other.length() == 0) {
            return this.atLeastOneLineIsAPoint(other) != null;
        }
        //if both lines are parallel to Y-axis
        if (this.b == 0 && other.b == 0) {
            return true;
        }
        // If the lines got same slope
        if (this.a / this.b == other.a / other.b) {
            return (this.commonXRange(other) > 0) // >= 1
                    && (this.c / this.b == other.c / other.b);
        }
        //calculate the intersection point
        return this.differentSlope(other) != null;
    }

    /**
     * This method return the intersection point
     * (if there is only one).
     *
     * @param other The other line
     * @return the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        boolean commonRanges = this.commonXRange(other) >= 1 && this.commonYRange(other) >= 1;
        // if they don't have common definition range
        if (!commonRanges) {
            return null;
        }
        // if at least one of the lines is a point
        if (this.length() == 0 || other.length() == 0) {
            return this.atLeastOneLineIsAPoint(other);
        }
        // if at least one of the lines is parallel to Y-axis
        if (this.b == 0 && other.b == 0) {
            return this.bothLinesAreParallel(other);
        }
        // If both lines got same slope
        if (this.a / this.b == other.a / other.b) {
            return this.sameSlope(other);
        }
        //calculate the intersection point
        return this.differentSlope(other);
    }

    /**
     * This method returns the closest intersection point
     * of the rectangle with this line.
     *
     * @param rect the rectangle.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interPoints = rect.intersectionPoints(this);
        if (interPoints.isEmpty()) {
            return null;
        }
        if (interPoints.size() == 1
                || this.start.distance(interPoints.get(0))
                < this.start.distance(interPoints.get(1))) {
            return interPoints.get(0);
        }
        return interPoints.get(1);
    }
}