import edu.macalester.graphics.*;
import edu.macalester.graphics.Image;
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
    private SquareManager squareManager;
    private Image startScreen = new Image(-50, -50, "title_screen.png");
    private Image loseScreen = new Image(-50, -50, "lose_screen.png");
    private Image winScreen = new Image(-50, -50, "win_screen.png");


    public StartGame() {
        canvas = new CanvasWindow("2048", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvas);
        squareManager = new SquareManager(background);
        canvas.add(startScreen);
    }

    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }

    /**
     * Handles running the game: handles inputs and reprints the screen for each input.
     */
    public void run() {
        canvas.onClick(event -> {
            if(squareManager.state.equals("notStarted")){
                canvas.removeAll();
                canvas.add(background);
                canvas.add(squareManager);
                squareManager.state="running";
                squareManager.reprint();
            }
            else if(squareManager.state.equals("over") || squareManager.state.equals("won")){
                int oldHighScore = squareManager.highestScore;     
                squareManager = new SquareManager(background);
                squareManager.score=0;
                squareManager.highestScore = oldHighScore;
                background.updateScore(0, "currentScore");
                run();       
            }
        });
        
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
            if(squareManager.state.equals("running")){
                squareManager.reprint();
            }
            else if(squareManager.state.equals("over")){
                canvas.removeAll();
                canvas.add(loseScreen);
            }
            else if(squareManager.state.equals("won")){
                canvas.removeAll();
                canvas.add(winScreen);
            }
        });
    }

    @Override
    public String toString() {
        return "StartGame [background=" + background + ", canvas=" + canvas + ", squareManager=" + squareManager + "]";
    }
}