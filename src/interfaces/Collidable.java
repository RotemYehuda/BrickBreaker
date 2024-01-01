// Rotem yehuda 313223968

package interfaces;
import game.Velocity;
import geometry.Rectangle;
import geometry.Point;
import sprites.Ball;


/**
 * This interface represent collidable objects.
 */
public interface Collidable {
    /**
     * This method returns the collision shape.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method calculates the new velocity after the collision.
     *
     * @param hitter          the ball that hits the collidable objecy.
     * @param collisionPoint  the point in which the objects collide.
     * @param currentVelocity the velocity of the object before it collided.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}