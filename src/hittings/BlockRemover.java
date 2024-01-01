//Rotem Yehuda 313223968

package hittings;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;
import game.Game;

/**
 * public class hittings.BlockRemover.
 *
 * @author Rotem Yehuda 313223968
 * This class is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game   the game which the blocks removes from.
     * @param blocks the amount of remaining blocks.
     */
    public BlockRemover(Game game, Counter blocks) {
        this.game = game;
        this.remainingBlocks = blocks;
    }

    /**
     * This method removes the block from the game
     * and removes this listener.
     *
     * @param beingHit the object that being hit.
     * @param hitter   the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}