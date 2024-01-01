// Rotem Yehuda 313223968

package collections;

import interfaces.Sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * public class collections.SpriteCollection.
 *
 * @author Rotem Yehuda
 * This class represents a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor- creates a list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * This method adds a sprite object to the collection.
     *
     * @param s new sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }


    /**
     * This method removes a sprite object from the collection.
     *
     * @param s new sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * This method call drawOn(d) on all sprites.
     *
     * @param d the DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}