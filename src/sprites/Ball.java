// Rotem Yehuda 313223968

package sprites;

import biuoop.DrawSurface;
import collections.GameEnvironment;
import geometry.Line;
import geometry.Point;
import interfaces.Sprite;
import hittings.CollisionInfo;
import game.Velocity;
import game.Game;

import java.awt.Color;

/**
 * public class sprites.Ball.
 *
 * @author Rotem Yehuda 313223968
 * This class represents a ball
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnv;

    /**
     * Constructor for ball from the center point.
     *
     * @param gameEnv The game environment
     * @param center  The center point of the ball
     * @param r       The radius of the ball
     * @param color   The color of the ball
     */
    public Ball(GameEnvironment gameEnv, Point center, int r, Color color) {
        this.gameEnv = gameEnv;
        this.setCenter(center);
        this.radius = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Constructor for ball from the center point.
     *
     * @param center The center point of the ball
     * @param r      The radius of the ball
     * @param color  The color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.gameEnv = new GameEnvironment();
        this.setCenter(center);
        this.radius = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Constructor for ball from the center's point coordinates.
     *
     * @param gameEnv The game environment
     * @param x       The center's point x coordinate
     * @param y       The center's point y coordinate
     * @param r       The radius of the ball
     * @param color   The color of the ball
     */
    public Ball(GameEnvironment gameEnv, int x, int y, int r, Color color) {
        this.gameEnv = gameEnv;
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Constructor for ball from the center's point coordinates.
     *
     * @param x     The center's point x coordinate
     * @param y     The center's point y coordinate
     * @param r     The radius of the ball
     * @param color The color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.gameEnv = new GameEnvironment();
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * This method sets the ball's center in integers.
     *
     * @param c the ball center.
     */
    public void setCenter(Point c) {
        this.center = new Point((int) c.getX(), (int) c.getY());
    }

    /**
     * This method sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method sets the velocity of the ball.
     *
     * @param dx the change in the X-axis
     * @param dy the change in the Y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This method sets the ball color.
     *
     * @param c the new ball's color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * This method sets the ball's radius.
     *
     * @param r the new ball's radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * This method sets the ball's game environment.
     *
     * @param gameEnvironment the new environment.
     */
    public void setGameEnv(GameEnvironment gameEnvironment) {
        this.gameEnv = gameEnvironment;
    }

    /**
     * This method returns the x coordinate of the ball's center.
     *
     * @return x coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method returns the y coordinate of the ball's center.
     *
     * @return y coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This method returns the ball's size.
     *
     * @return The ball's radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This method returns the ball's color.
     *
     * @return The color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method return the velocity of the ball.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method returns the game's environment.
     *
     * @return the ball's game environment
     */
    public GameEnvironment getGameEnv() {
        return this.gameEnv;
    }

    /**
     * This method draws the ball (filled circle) on given drawSurface.
     *
     * @param surface The drawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * This method moves the ball according to
     * his velocity and boundaries.
     */
    public void moveOneStep() {
        //the end of the trajectory.
        Point newCenter = this.getVelocity().applyToPoint(this.center);

        //compute ball trajectory.
        Line trajectory = new Line(this.center, newCenter);

        //returns the information about the closest collision.
        CollisionInfo collisionInfo = this.gameEnv.getClosestCollision(trajectory);

        if (collisionInfo != null) {
            this.setCenter(new Point(collisionInfo.collisionPoint().getX() - this.velocity.getDx(),
                    collisionInfo.collisionPoint().getY() - this.velocity.getDy()));
            Velocity newVelocity = collisionInfo.collisionObject().
                    hit(this, collisionInfo.collisionPoint(), this.velocity);
            this.velocity = newVelocity;
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * This method moves the ball one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds this ball to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * This method removes this ball from the given game.
     *
     * @param game the given game to remove the ball from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}