// Rotem yehuda 313223968

package collections;

import interfaces.Collidable;
import geometry.Line;
import geometry.Point;
import hittings.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * public class collections.GameEnvironment.
 *
 * @author Rotem Yehuda
 * This class represents the game's environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor- creates a list of collideable objects.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * This method removes a given collidable from the environment.
     *
     * @param c collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This method adds a given collidable to the environment.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This method returns the information about the collision
     * with the closest collidable object.
     *
     * @param trajectory the object's expected trajectory.
     * @return null if there is no collidable objects,
     * or the object will not collide with any of the collideables.
     * otherwise it returns the information about the closest
     * collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //in case there is no collideable objects.
        if (this.collidables.isEmpty()) {
            return null;
        }

        // finds the first collidable object the object collided with.
        int firstCollisionIndex = 0;
        boolean isCollided = false;
        for (int i = 0; i < this.collidables.size(); i++) {
            if (getCollision(trajectory, i) != null) {
                firstCollisionIndex = i;
                isCollided = true;
                break;
            }
        }

        // in case there is collidable objects but no collision points.
        if (!isCollided) {
            return null;
        }

        //the index of the collidable object with the closest collision point.
        int closest = firstCollisionIndex;
        // calculate the distance from the trajectory's start to the closest
        // collision point with the first collided object.
        double minDistance = trajectory.start().distance(getCollision(trajectory, closest));

        //checks if the is collidable object with closer collision point than the first one.
        for (int i = firstCollisionIndex + 1; i < this.collidables.size(); i++) {
            Point point = getCollision(trajectory, i);
            if (point != null && trajectory.start().distance(point) < minDistance) {
                closest = i;
                minDistance = trajectory.start().distance(point);
            }
        }
        return new CollisionInfo(getCollision(trajectory, closest), collidables.get(closest));
    }

    /**
     * This method returns the closest collision point with
     * the object.
     *
     * @param line  the line we may intersect.
     * @param index the index of the object in collidables.
     * @return the closest intersection point of the line and the object.
     */
    public Point getCollision(Line line, int index) {
        return line.closestIntersectionToStartOfLine(this.collidables.
                get(index).getCollisionRectangle());
    }
}