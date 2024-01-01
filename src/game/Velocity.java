// Rotem Yehuda 313223968

package game;

import geometry.Point;

/**
 * public class game.Velocity.
 *
 * @author Rotem Yehuda 313223968
 * This class represents velocity
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor- from angle and speed.
     *
     * @param angle the movement direction
     * @param speed the units (the length) of the movement
     * @return game.Velocity of the object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Constructor-  from the change in x and y axis.
     *
     * @param dx the change in x axis
     * @param dy the change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method returns the length of the movement.
     *
     * @return the vector's length.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * This method returns the movement's angle.
     *
     * @return the movement's angle.
     */
    public double getAngle() {
        double angle = Math.toDegrees(Math.atan2(this.dy, this.dx)) + 90;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * This method return the dx value.
     *
     * @return dx the change in x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method return the dy value.
     *
     * @return dy the change in y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method moves a point.
     *
     * @param p point with position (x,y)
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}