//Rotem Yehuda 313223968

package hittings;

/**
 * public class hittings.Counter.
 *
 * @author Rotem Yehuda 313223968
 * This class is used for counting things.
 */
public class Counter {
    private int counter = 0;

    /**
     * Constructor.
     *
     * @param initialValue the initial value of the counter.
     */
    public Counter(int initialValue) {
        this.counter = initialValue;
    }

    /**
     * This method add number to current count.
     *
     * @param number the number we add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * This method subtract number from current count.
     *
     * @param number the number we subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * This method returns the current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}