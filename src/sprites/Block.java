// Rotem yehuda 313223968

package sprites;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import game.Velocity;
import game.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * public class sprites.Block.
 *
 * @author Rotem Yehuda
 * This class represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;

    /**
     * Constructor- from rectangle and color
     * for unlimited hits ball.
     *
     * @param rect  the rectangle that defines the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hits = (int) Double.POSITIVE_INFINITY;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor- from rectangle and color.
     *
     * @param rect  the rectangle that defines the block.
     * @param color the color of the block.
     * @param hits  the amount of possible hits.
     */
    public Block(Rectangle rect, Color color, int hits) {
        this.rectangle = rect;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor- from the rectangle's upper left point,
     * measurements and color,
     * for unlimited hits ball.
     *
     * @param upperLeft the point of the upper left point of
     *                  the rectangle that defines the block.
     * @param width     the width of the rectangle that defines the block.
     * @param height    the height of the rectangle that defines the block.
     * @param color     the color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hits = (int) Double.POSITIVE_INFINITY;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor- from the rectangle's upper left point,
     * measurements and color.
     *
     * @param upperLeft the point of the upper left point of
     *                  the rectangle that defines the block.
     * @param width     the width of the rectangle that defines the block.
     * @param height    the height of the rectangle that defines the block.
     * @param color     the color of the block.
     * @param hits      the amount of possible hits.
     */
    public Block(Point upperLeft, double width, double height, Color color, int hits) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor- from the rectangle's upper left point's
     * coordinates, and the rectangle's measurements and color,
     * for unlimited hits ball.
     *
     * @param x      the x coordinate of the upper left point
     * @param y      the y coordinate of the upper left point
     * @param width  the width of the rectangle that defines the block.
     * @param height the height of the rectangle that defines the block.
     * @param color  the color of the block.
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hits = (int) Double.POSITIVE_INFINITY;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor- from the rectangle's upper left point's
     * coordinates, and the rectangle's measurements and color.
     *
     * @param x      the x coordinate of the upper left point
     * @param y      the y coordinate of the upper left point
     * @param width  the width of the rectangle that defines the block.
     * @param height the height of the rectangle that defines the block.
     * @param color  the color of the block.
     * @param hits   the amount of possible hits.
     */
    public Block(double x, double y, double width, double height, Color color, int hits) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method sets the block's color.
     *
     * @param c The new color.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * This method sets the amount of possible hits.
     *
     * @param newHits the amount of hits.
     */
    public void setHits(int newHits) {
        this.hits = newHits;
    }

    /**
     * This method returns the block's color.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method returns.
     *
     * @return the number.
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * This method returns the "collision shape" of the block.
     *
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method checks if the object collide with the block,
     * and if it does, it calculate the new velocity.
     *
     * @param hitter          the ball that hits this block.
     * @param collisionPoint  the point in which the objects collide.
     * @param currentVelocity the velocity of the object before it collided.
     * @return The new velocity after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean hitsHorizontalEdge = this.rectangle.getUpperEdge().containThePoint(collisionPoint)
                || this.rectangle.getLowerEdge().containThePoint(collisionPoint);
        boolean hitsVerticalEdge = this.rectangle.getLeftEdge().containThePoint(collisionPoint)
                || this.rectangle.getRightEdge().containThePoint(collisionPoint);
        if ((hitsHorizontalEdge || hitsVerticalEdge) && this.hits > 0) {
            this.hits--;
            this.notifyHit(hitter);
        }
        if (hitsHorizontalEdge && hitsVerticalEdge) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (hitsHorizontalEdge) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (hitsVerticalEdge) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * This method draws this block on given drawSurface.
     *
     * @param surface The drawSurface
     */
    public void drawOn(DrawSurface surface) {
        //fills the block
        surface.setColor(this.color);
        surface.fillRectangle(
                (int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        //draws the borders of the block
        surface.setColor(Color.lightGray);
        surface.drawRectangle(
                (int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Not implement yet.
     */
    public void timePassed() {
    }

    /**
     * This method adds this block to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This method removes this block from the given game.
     *
     * @param game the given game to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notify to the hit listeners when a ball hits the block.
     *
     * @param hitter the ball that hits this block.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}