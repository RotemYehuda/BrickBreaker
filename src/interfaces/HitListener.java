//Rotem Yehuda 313223968

package interfaces;

import sprites.Block;
import sprites.Ball;

/**
 * This interface represents a hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
