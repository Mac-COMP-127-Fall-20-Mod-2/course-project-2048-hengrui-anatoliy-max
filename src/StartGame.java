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

     public StartGame() {
        canvas = new CanvasWindow("2048", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvas); 
        canvas.add(background);
        square = new Square(40, 461, 2048);
        canvas.add(square);
     }

    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }

    public void run() {
        canvas.draw();

        canvas.onKeyDown(event -> {
            if(canvas.getKeysPressed().contains(Key.UP_ARROW)) {  
            square.move("Up");
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.DOWN_ARROW)) {
            square.move("Down");
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.RIGHT_ARROW)) {
            square.move("Right");
            canvas.draw();
        } else if (canvas.getKeysPressed().contains(Key.LEFT_ARROW)) {
            square.move("Left");
            canvas.draw();
        }
        });
    }
         

}
