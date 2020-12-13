import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;


/**
 * Represents the background with gridlines for the squares to exist on.
 */
public class Background extends GraphicsGroup {
    
    private double margin;
    private double x;
    private double y;
    private CanvasWindow canvas;
    

    /**
     * Constructs the background on the specified canvas.
     */
    public Background(CanvasWindow canvas) {
        this.canvas= canvas;
        this.margin = 40;
        this.x = margin;
        this.y = canvas.getHeight()*0.068;
        this.draw();    
    }

    /**
     * Draws rectangles and squares that serve as the background for the 2048 game.
     */
    private void draw() {
        Rectangle background = new Rectangle(0,0,600,600);
        background.setFillColor(Color.darkGray);
        this.add(background);
        
        for (int i = 0; i < 16; i++) {                      // creates a row of bricks
            Rectangle square = new Rectangle(x, y, 100, 100);
            square.setFillColor(Color.lightGray);
            this.add(square);
            if (x < canvas.getWidth()- 100 - margin) {
                x = x + 100 + margin;
            } 
            else {                                        // multiplies of the currently existent row of bricks 
                x = margin;
                y = y + 100 + margin;
            }
        }
    }   
}
