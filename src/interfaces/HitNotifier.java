//Rotem Yehuda 313223968

package interfaces;

/**
 * This interface represents a hit notifier.
 */
public interface HitNotifier {
    /**
     * This method add hl as a listener to hit events.
     *
     * @param hl hit listener object.
     */
    void addHitListener(HitListener hl);

    /**
     * This method removes hl as a listener to hit events.
     *
     * @param hl hit listener object.
     */
    void removeHitListener(HitListener hl);
}