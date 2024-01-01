//Rotem Yehuda 313223968

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collections.GameEnvironment;
import collections.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import hittings.BallRemover;
import hittings.BlockRemover;
import hittings.Counter;
import hittings.ScoreTrackingListener;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.Random;

/**
 * public class game.Game.
 *
 * @author Rotem Yehuda 313223968
 * This class sets the blocks, the balls and the paddle.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    // Frame's measurements
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FRAMES_THICK = 20;

    // information about the sprites
    public static final int NUM_ROWS = 6;
    public static final int MAX_BLOCKS_IN_A_ROW = 12;
    public static final int TOTAL_BLOCKS = (NUM_ROWS / 2) * (2 * MAX_BLOCKS_IN_A_ROW - (NUM_ROWS - 1));
    public static final int NUM_BALLS = 3;
    public static final double PADDLES_HEIGHT = 25;
    public static final double PADDLES_WIDTH = 100;


    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
    }

    /**
     * This method adds a collideable object to this collections.GameEnvironment.
     *
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method removes a collideable object from this collections.GameEnvironment.
     *
     * @param c the collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method adds a sprite to this sprites collection.
     *
     * @param s the sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method removes a sprite from this sprites collection.
     *
     * @param s the sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method initialize a new game:
     * create the Blocks, the sprites.Ball and the sprites.Paddle
     * and add them to the game.
     */
    public void initialize() {
        //creates the GUI
        this.gui = new GUI("GAME", WIDTH, HEIGHT);
        this.sleeper = new Sleeper();

        //creates and sets the frame
        setFrame();

        // creates and sets the blocks, the balls,
        // the paddle ant the score indicator.
        setBlocks();
        setBalls();
        setPaddle();
        setScoreIndicator();
    }

    /**
     * This method creates and add the frame's blocks to the game.
     */
    public void setFrame() {
        //creates the frame
        Block upperBound = new Block(FRAMES_THICK, 0,
                WIDTH - 2 * FRAMES_THICK, FRAMES_THICK, Color.GRAY);
        Block lowerBound = new Block(0, HEIGHT - FRAMES_THICK,
                WIDTH, FRAMES_THICK, Color.GRAY);
        Block leftBound = new Block(0, 0,
                FRAMES_THICK, HEIGHT - FRAMES_THICK, Color.GRAY);
        Block rightBound = new Block(WIDTH - FRAMES_THICK, 0,
                FRAMES_THICK, HEIGHT - FRAMES_THICK, Color.GRAY);

        // adds the frame's blocks
        upperBound.addToGame(this);
        lowerBound.addToGame(this);
        leftBound.addToGame(this);
        rightBound.addToGame(this);

        // sets the death region.
        HitListener ballRemover = new BallRemover(this, this.remainingBalls);
        lowerBound.addHitListener(ballRemover);
    }

    /**
     * This method creates and add the blocks to the game.
     */
    public void setBlocks() {
        // sets the rows colors
        Color[] colors = {new Color(153, 0, 204),
                new Color(204, 0, 255),
                new Color(204, 51, 255),
                new Color(204, 102, 255),
                new Color(204, 153, 255),
                new Color(204, 204, 255)};

        int blocksWidth = 60;
        int blocksHeight = 20;
        int upperBound = 100;

        Block[] blocks = new Block[TOTAL_BLOCKS];
        int numBlocks = MAX_BLOCKS_IN_A_ROW;

        HitListener blockRemover = new BlockRemover(this, this.remainingBlocks);
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        // creates 6 rows
        for (int i = 0; i < NUM_ROWS; i++) {
            int start = WIDTH - numBlocks * blocksWidth - FRAMES_THICK;
            for (int j = 0; j < numBlocks; j++) {
                blocks[i + j] = new Block(start + j * blocksWidth, upperBound + i * blocksHeight,
                        blocksWidth, blocksHeight, colors[i], 1);
                blocks[i + j].addToGame(this);
                this.remainingBlocks.increase(1);
                blocks[i + j].addHitListener(blockRemover);
                blocks[i + j].addHitListener(scoreTrackingListener);
            }
            numBlocks--;
        }
    }

    /**
     * This method creates and add the balls to the game.
     */
    public void setBalls() {
        // the balls radius and velocity range.
        int radius = 5;
        int minVelocity = 1;
        int maxVelocity = 5;

        Color ballsColor = new Color(255, 204, 0);
        Ball[] balls = new Ball[NUM_BALLS];

        for (int i = 0; i < NUM_BALLS; i++) {
            balls[i] = new Ball(this.environment, WIDTH / 2,
                    HEIGHT - FRAMES_THICK - (int) PADDLES_HEIGHT - 2 * radius,
                    radius, ballsColor);
            Random rand = new Random();
            balls[i].setVelocity(rand.nextInt(maxVelocity - minVelocity + 1) + minVelocity,
                    rand.nextInt(maxVelocity - minVelocity + 1) + minVelocity);
            balls[i].addToGame(this);
            this.remainingBalls.increase(1);
        }
    }

    /**
     * This method creates and add the paddle to the game.
     */
    public void setPaddle() {
        Point upperLeft = new Point((WIDTH - 2 * FRAMES_THICK) / 2 - PADDLES_WIDTH / 2,
                HEIGHT - FRAMES_THICK - PADDLES_HEIGHT);
        Rectangle rect = new Rectangle(upperLeft, PADDLES_WIDTH, PADDLES_HEIGHT);
        Paddle paddle = new Paddle(this.gui, rect, new Color(255, 102, 0), FRAMES_THICK);
        paddle.addToGame(this);
    }

    /**
     * This method sets the score indicator.
     */
    public void setScoreIndicator() {
        ScoreIndicator scoreInd = new ScoreIndicator(this.score);
        this.addSprite(scoreInd);
    }

    /**
     * This method runs the game-
     * start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Boolean stopRunningTheGame = this.remainingBlocks.getValue() <= 0
                || this.remainingBalls.getValue() <= 0;
        while (!stopRunningTheGame) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            stopRunningTheGame = this.remainingBlocks.getValue() <= 0
                    || this.remainingBalls.getValue() <= 0;
        }
        // adds 100 points when all the blocks are removed
        this.score.increase(100);

        gui.close();
        return;
    }
}