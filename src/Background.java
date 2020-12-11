import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class Background extends GraphicsGroup {
    
    double margin;
    double x;
    double y;
    double length = 0;
    private CanvasWindow canvas;
    
    public Background(CanvasWindow canvas) {
        this.canvas= canvas;
        this.margin = 40;
        this.x = margin;
        this.y = canvas.getHeight()*0.1;
        this.draw();    
    }
    public void draw() {
        
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
