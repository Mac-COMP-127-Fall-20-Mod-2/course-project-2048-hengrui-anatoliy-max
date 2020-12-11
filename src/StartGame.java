/**
 * Group Name:
 * Description:
 * Reference:
 * 
 */
import edu.macalester.graphics.*;


 public class StartGame{
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;

    private CanvasWindow canvas;


    public StartGame() {
        canvas = new CanvasWindow("2048", CANVAS_WIDTH, CANVAS_HEIGHT);
    }
     



    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }


    public void run() {
        canvas.draw();
    }

 }