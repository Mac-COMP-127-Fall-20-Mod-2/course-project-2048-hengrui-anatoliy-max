/**
 * Group Name:
 * Description:
 * Reference:
 * 
 */

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

 public class StartGame {
     private static final int CANVAS_WIDTH = 600;
     private static final int CANVAS_HEIGHT = 600;

     private CanvasWindow canvas;
     private Square square;
     private Background background;
     SquareManager squareManager;

     public StartGame() {
        canvas = new CanvasWindow("2048", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvas); 
        canvas.add(background);
        squareManager = new SquareManager(canvas);
        canvas.add(squareManager);
        
        // square = new Square(40, 461, 8);
        // canvas.add(square);
     }

    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }

    public void run() {
        squareManager.generate();
        canvas.draw();

        canvas.onKeyDown(event -> {
            if(canvas.getKeysPressed().contains(Key.UP_ARROW)) {  
            squareManager.move("Up");
            squareManager.generate();
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.DOWN_ARROW)) {
            squareManager.move("Down");
            squareManager.generate();
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.RIGHT_ARROW)) {
            squareManager.move("Right");
            squareManager.generate();
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.LEFT_ARROW)) {
            squareManager.move("Left");
            squareManager.generate();
            canvas.draw();
        }
        });
    }
         

}
