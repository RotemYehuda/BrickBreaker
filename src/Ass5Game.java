//Rotem Yehuda 313223968

import game.Game;

/**
 * public class Ass5Game.
 *
 * @author Rotem Yehuda 313223968
 * This class calls themain methos
 * and start the game.
 */
public class Ass5Game {
    /**
     * This is the main method who runs the game.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
