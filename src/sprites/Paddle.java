//Rotem Yehuda 313223968

package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import game.Velocity;
import game.Game;

import java.awt.Color;

/**
 * public class sprites.Paddle.
 *
 * @author Rotem Yehuda 313223968
 * This class represents the paddle,
 * the player in the game.
 */
public class Paddle implements Sprite, Collidable {
    private GUI gui;
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int framesThick;

    private static int speed = 5;


    /**
     * Constructor from rectangle and color.
     *
     * @param gui   the given GUI.
     * @param rect  the rectangle that defines the paddle.
     * @param color the color of the paddle.
     * @param thick the width of the paddle's environment frame.
     */
    public Paddle(GUI gui, Rectangle rect, Color color, int thick) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = rect;
        this.color = color;
        this.framesThick = thick;
    }

    /**
     * Constructor- from the rectangle's upper left point,
     * width, height and color.
     *
     * @param gui       the given GUI.
     * @param upperLeft the upper left point of
     *                  the rectangle that defines the paddle.
     * @param width     the width of the rectangle that defines the paddle.
     * @param height    the height of the rectangle that defines the paddle.
     * @param color     the color of the paddle.
     * @param thick     the width of the paddle's environment frame.
     */
    public Paddle(GUI gui, Point upperLeft, double width, double height, Color color, int thick) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.framesThick = thick;
    }

    /**
     * @param gui    the given GUI.
     * @param x      the x coordinate of the upper left point of
     *               the rectangle that defines the paddle.
     * @param y      the y coordinate of the upper left point of
     *               the rectangle that defines the paddle.
     * @param width  the width of the rectangle that defines the paddle.
     * @param height the height of the rectangle that defines the paddle.
     * @param color  the color of the paddle.
     * @param thick  the width of the paddle's environment frame.
     */
    public Paddle(GUI gui, double x, double y, double width, double height, Color color, int thick) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.framesThick = thick;
    }

    /**
     * This method moves the paddle's rectangle one step to the left.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - speed;
        if (newX >= this.framesThick) {
            this.rectangle.moveRect(new Point(newX, this.rectangle.getUpperLeft().getY()));
        }
    }

    /**
     * This method moves the paddle's rectangle one step to the right.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + speed;
        if (newX + this.rectangle.getWidth()
                <= this.gui.getDrawSurface().getWidth() - this.framesThick) {
            this.rectangle.moveRect(new Point(newX, this.rectangle.getUpperLeft().getY()));
        }
    }

    /**
     * This method checks if the left or right key are pressed
     * and moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * This method draws the paddle's
     * rectangle on the DrawSurface.
     *
     * @param d the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(
                (int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * This method returns the "collision shape" of the paddle.
     *
     * @return the rectangle that defines the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param hitter          the ball that hits the paddle.
     * @param collisionPoint  the point in which the objects collide.
     * @param currentVelocity the velocity of the object before it collided.
     * @return The new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.rectangle.getUpperEdge().containThePoint(collisionPoint)) {
            return hitsUpperEdge(collisionPoint, currentVelocity);
        }
        // in case it hits the left or right edeges.
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * This method returns the new velocity according to the hit's region.
     *
     * @param collisionPoint  the point in which the objects collide.
     * @param currentVelocity the velocity of the object before it collided.
     * @return The new velocity after the collision.
     */
    public Velocity hitsUpperEdge(Point collisionPoint, Velocity currentVelocity) {
        double sectionLength = this.rectangle.getUpperEdge().length() / 5;
        double yValue = this.rectangle.getUpperLeft().getY();
        Line firstRegion = new Line(this.rectangle.getUpperLeft().getX(), yValue,
                this.rectangle.getUpperLeft().getX() + sectionLength, yValue);
        if (firstRegion.containThePoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        Line secondRegion = new Line(this.rectangle.getUpperLeft().getX() + sectionLength,
                yValue, this.rectangle.getUpperLeft().getX() + 2 * sectionLength, yValue);
        if (secondRegion.containThePoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        Line thirdRegion = new Line(this.rectangle.getUpperLeft().getX() + 2 * sectionLength,
                yValue, this.rectangle.getUpperLeft().getX() + 3 * sectionLength, yValue);
        if (thirdRegion.containThePoint(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        Line fourthRegion = new Line(this.rectangle.getUpperLeft().getX() + 3 * sectionLength,
                yValue, this.rectangle.getUpperLeft().getX() + 4 * sectionLength, yValue);
        if (fourthRegion.containThePoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        //in case it hits the fifth region
        return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
    }

    /**
     * This method adds this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}