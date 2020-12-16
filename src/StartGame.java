import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

/**
 * Group Name: Hengrui, Anatoliy, Max Description: The classic game 2048. Players move squares
 * around on the screen which can merge into a single box of the sum of their values when they
 * collide! Try to get the fabled 2048 square. References:
 * 
 */
public class StartGame {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private CanvasWindow canvas;
    private Background background;
    SquareManager squareManager;


    public StartGame() {
        canvas = new CanvasWindow("2048", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvas);
        canvas.add(background);
        squareManager = new SquareManager(background);
        canvas.add(squareManager);
        squareManager.reprint();
    }

    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }

    /**
     * Handles running the game: handles inputs and reprints the screen for each input.
     */
    public void run() {
        canvas.onKeyDown(event -> {
            if (canvas.getKeysPressed().contains(Key.UP_ARROW)) {
                squareManager.move(0, 0, -1);
            } else if (canvas.getKeysPressed().contains(Key.DOWN_ARROW)) {
                squareManager.move(15, 0, 1);
            } else if (canvas.getKeysPressed().contains(Key.RIGHT_ARROW)) {
                squareManager.move(15, 1, 0);
            } else if (canvas.getKeysPressed().contains(Key.LEFT_ARROW)) {
                squareManager.move(0, -1, 0);
            }
            squareManager.reprint();
        });
    }

    @Override
    public String toString() {
        return "StartGame [background=" + background + ", canvas=" + canvas + ", squareManager=" + squareManager + "]";
    }
}