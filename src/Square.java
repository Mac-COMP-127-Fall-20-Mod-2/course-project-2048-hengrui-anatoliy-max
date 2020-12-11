import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class Square extends GraphicsGroup {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private int value;
    private Color color;
    private double x;
    private double y;

    public Square(double x, double y, Color color, int value) {
        this.x = x;
        this.y = y;
        this.color = color;

        createBoxDrawing();
    }

    private void createBoxDrawing() {
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        rectangle.setFillColor(color);
        add(rectangle);
    }

    public void move(String direction) {
        if(direction.equals("Up")&&y>60) {
            y=y-140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Down")&&y<=340) {
            y=y+140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Left")&&x>40) {
            x=x-140;
            this.setCenter(x+50,y+50);
        }
        else if(direction.equals("Right")&&x<=320) {
            x=x+140;
            this.setCenter(x+50,y+50);
        }
    }

}