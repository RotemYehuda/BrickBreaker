//Rotem Yehuda 313223968

package hittings;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;
import game.Game;

/**
 * public class hittings.BallRemover.
 *
 * @author Rotem Yehuda 313223968
 * This class is in charge of removing the ball from the game
 * when it hits the bottom of the screen.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game  the game which the blocks removes from.
     * @param balls the amount of remaining balls.
     */
    public BallRemover(Game game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}