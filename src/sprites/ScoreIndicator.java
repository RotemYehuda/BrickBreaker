//Rotem Yehuda 313223968

package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;
import hittings.Counter;
import game.Game;

import java.awt.Color;

/**
 * This class is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle scoreDisplay;
    public static final int RECT_WIDTH = 100;
    public static final int SPACE = 3;

    /**
     * Constructor.
     *
     * @param score the given score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.scoreDisplay = new Rectangle(((Game.WIDTH - RECT_WIDTH) / 2), SPACE,
                RECT_WIDTH, Game.FRAMES_THICK - 2 * SPACE);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle((int) this.scoreDisplay.getUpperLeft().getX(),
                (int) this.scoreDisplay.getUpperLeft().getY(),
                (int) this.scoreDisplay.getWidth(),
                (int) this.scoreDisplay.getHeight());
        d.setColor(new Color(153, 0, 153));
        d.drawText((int) this.scoreDisplay.getUpperLeft().getX() + 20,
                (int) this.scoreDisplay.getHeight() - SPACE / 2,
                "SCORE: " + Integer.toString(this.score.getValue()),
                10);
    }

    @Override
    public void timePassed() {
    }

    /**
     * This method adds this score indicator to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}