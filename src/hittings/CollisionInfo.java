// Rotem yehuda 313223968

package hittings;

import geometry.Point;
import interfaces.Collidable;

/**
 * public class hittings.CollisionInfo.
 *
 * @author Rotem Yehuda
 * This class represents collidable object that
 * involved in the collision and the point
 * in which the collision occurs.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidedObject;

    /**
     * Constructor.
     *
     * @param collisionPoint the collision point.
     * @param collidedObject the object collided with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidedObject) {
        this.collisionPoint = collisionPoint;
        this.collidedObject = collidedObject;
    }

    /**
     * This method returns the collision point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * This method return the collided object.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidedObject;
    }
}