//Rotem Yehuda 313223968

package hittings;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * This class is in charge to update this counter when blocks
 * are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the given score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}