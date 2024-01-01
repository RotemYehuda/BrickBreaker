// Rotem yehuda 313223968

package interfaces;

import biuoop.DrawSurface;

/**
 * This interface represent objects that are sprites.
 */
public interface Sprite {

    /**
     * This method draws the sprite to the screen.
     *
     * @param d the DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     */
    void timePassed();
}